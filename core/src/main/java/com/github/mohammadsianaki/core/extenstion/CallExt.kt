package com.github.mohammadsianaki.core.extenstion

import com.github.mohammadsianaki.core.model.ErrorHolder
import com.github.mohammadsianaki.core.model.Result
import kotlinx.coroutines.suspendCancellableCoroutine
import retrofit2.Call
import retrofit2.Callback
import retrofit2.HttpException
import retrofit2.Response
import java.io.IOException
import kotlin.coroutines.resume

suspend fun <T, R> Call<T>.awaitResult(map: (T) -> R): Result<R> =
    suspendCancellableCoroutine { continuation ->
        try {
            enqueue(object : Callback<T> {
                override fun onFailure(call: Call<T>, t: Throwable) {
                    errorHappened(t)
                }

                override fun onResponse(call: Call<T>, response: Response<T>) {
                    if (response.isSuccessful) {
                        try {
                            continuation.resume(Result.Success(map(response.body()!!)))
                        } catch (ex: Exception) {
                            errorHappened(ex)
                        }
                    }
                }

                private fun errorHappened(throwable: Throwable) {
                    continuation.resume(
                        Result.Failure(
                            asReadableException(
                                throwable
                            )
                        )
                    )
                }
            })

        } catch (th: Throwable) {
            continuation.resume(
                Result.Failure(
                    asReadableException(
                        th
                    )
                )
            )
        }
        continuation.invokeOnCancellation {
            cancel()
        }
    }

private fun asReadableException(throwable: Throwable): ErrorHolder = when (throwable) {
    is IOException -> ErrorHolder.NetworkConnection("No Internet Connection", throwable)
    is HttpException -> ErrorHolder.Server("Server Error", throwable)
    else -> ErrorHolder.UnExpected(throwable.message ?: "Something went wrong...")
}