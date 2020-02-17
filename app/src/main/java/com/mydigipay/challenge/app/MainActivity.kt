package com.mydigipay.challenge.app

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.mydigipay.challenge.extentions.cast
import com.mydigipay.challenge.github.R
import com.mydigipay.challenge.network.oauth.GithubApiService
import com.mydigipay.challenge.network.oauth.RequestAccessToken
import com.mydigipay.challenge.utils.*
import kotlinx.coroutines.*
import org.koin.android.ext.android.inject
import retrofit2.HttpException


@ExperimentalCoroutinesApi
@FlowPreview
class MainActivity : AppCompatActivity() {

    private val githubApiService: GithubApiService by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initEvents()
    }

    private fun initEvents() {
        EventBus.instance.collectEventOnMainThread<NetworkErrorEvent> { event ->
            if (event.throwable is HttpException)
                event.throwable.cast<HttpException>().handle(event.onRetry)

        }
    }

    fun HttpException.handle(onRetry: (() -> Unit)? = null) {
        when (code()) {
            401 -> {
                Coroutines.io {
                    githubApiService.accessToken(RequestAccessToken.DEFAULT).await().accessToken
                        ?.let {
                            token = it
                            onRetry?.invoke()
                        }
                }
            }
        }
    }
}
