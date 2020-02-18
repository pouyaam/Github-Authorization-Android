package com.mydigipay.challenge.utils

import com.mydigipay.challenge.network.oauth.GithubApiService
import com.mydigipay.challenge.network.oauth.RequestAccessToken
import kotlinx.coroutines.*
import org.koin.core.KoinComponent
import retrofit2.HttpException
import java.io.IOException
import java.net.UnknownHostException

@UseExperimental(FlowPreview::class)
typealias CompletionBlock<T> = Coroutines.Request<T>.() -> Unit


@FlowPreview
@ExperimentalCoroutinesApi
object Coroutines : KoinComponent {

    private val githubApiService: GithubApiService = getKoin().get()
    private val eventBus: EventBus = getKoin().get()

    fun <T : Any> io(parentJob: Job? = null, work: suspend (() -> T?)): Job {
        val job = parentJob ?: Job()
        CoroutineScope(Dispatchers.IO + job).async {
            work()
        }
        return job
    }

    fun <T : Any?> ioThenMain(
        parentJob: Job? = null,
        work: suspend (() -> T),
        completionBlock: CompletionBlock<T>? = null
    ): Job {
        val callback = Request<T>().apply { completionBlock?.let { it() } }
        val handler = CoroutineExceptionHandler { _, exception ->
            if (handleGlobalException(exception, work, completionBlock))
                return@CoroutineExceptionHandler
            callback.invoke(exception)
            callback.invokeFinally()
        }
        val job = parentJob ?: Job()
        CoroutineScope(Dispatchers.Main + handler).launch {
            callback.invokeOnExecute()
            val data = CoroutineScope(Dispatchers.IO + job).async {
                return@async work()
            }.await()
            callback.invoke(data)
            callback.invokeFinally()
        }
        return job
    }

    private fun <T : Any?> handleGlobalException(
        exception: Throwable,
        work: suspend () -> T,
        completionBlock: CompletionBlock<T>?
    ): Boolean {
        if (exception is HttpException) {
            return exception.handle(onRetry = {
                ioThenMain(work = work, completionBlock = completionBlock)
            })
        } else if (exception is UnknownHostException || exception is IOException) {
            eventBus.send(NetworkErrorEvent(exception, onRetry = {
                ioThenMain(work = work, completionBlock = completionBlock)
            }))
            return true
        }

        return false
    }

    private fun HttpException.handle(
        parentJob: Job? = null,
        onRetry: (() -> Unit)? = null
    ): Boolean {
        return when (code()) {
            401 -> {
                io(parentJob) {
                    githubApiService.accessToken(RequestAccessToken.DEFAULT).await().accessToken
                        ?.let {
                            token = it
                            onRetry?.invoke()
                        }
                }
                true
            }
            else -> false
        }
    }

    class Request<T> {
        private var onComplete: ((T) -> Unit)? = null
        private var onError: ((Throwable) -> Unit)? = null
        private var onCancel: ((CancellationException) -> Unit)? = null
        private var onExecute: (() -> Unit)? = null
        private var finally: (() -> Unit)? = null

        fun onComplete(block: (T) -> Unit) {
            onComplete = block
        }

        fun onError(block: (Throwable) -> Unit) {
            onError = block
        }

        @Synchronized
        fun onExecute(block: () -> Unit) {
            onExecute = block
        }

        @Synchronized
        fun finally(block: () -> Unit) {
            finally = block
        }

        operator fun invoke(result: T) {
            onComplete?.invoke(result)
        }

        fun invokeOnExecute() {
            onExecute?.invoke()
        }

        fun invokeFinally() {
            finally?.invoke()
        }

        operator fun invoke(error: Throwable) {
            onError?.invoke(error)
        }

        operator fun invoke(error: CancellationException) {
            onCancel?.invoke(error)
        }
    }
}