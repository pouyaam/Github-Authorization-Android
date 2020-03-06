package com.mydigipay.challenge.core.network

import okhttp3.Request
import retrofit2.*
import java.io.IOException
import java.lang.reflect.ParameterizedType
import java.lang.reflect.Type

sealed class RetrofitResult<out T> {
    data class Success<T>(val data: T?) : RetrofitResult<T>()
    data class Error(val statusCode: Int?, val e: Exception) : RetrofitResult<Nothing>()
}

abstract class CallDelegate<TIn, TOut>(
    protected val proxy: Call<TIn>
) : Call<TOut> {
    override fun execute(): Response<TOut> = throw NotImplementedError()
    final override fun enqueue(callback: Callback<TOut>) = enqueueImpl(callback)
    final override fun clone(): Call<TOut> = cloneImpl()

    override fun cancel() = proxy.cancel()
    override fun request(): Request = proxy.request()
    override fun isExecuted() = proxy.isExecuted
    override fun isCanceled() = proxy.isCanceled

    abstract fun enqueueImpl(callback: Callback<TOut>)
    abstract fun cloneImpl(): Call<TOut>
}

class ResultCall<T>(proxy: Call<T>) : CallDelegate<T, RetrofitResult<T>>(proxy) {
    override fun enqueueImpl(callback: Callback<RetrofitResult<T>>) =
        proxy.enqueue(object : Callback<T> {
            override fun onResponse(call: Call<T>, response: Response<T>) {

                val result = when (val code = response.code()) {
                    in 200..299 -> {
                        val body = response.body()
                        RetrofitResult.Success(body)
                    }

                    401 -> RetrofitResult.Error(
                        code,
                        Exception("UnAuthorized")
                    )
                    in 400..499 -> RetrofitResult.Error(
                        code,
                        Exception("ClientError")
                    )
                    in 500..599 -> RetrofitResult.Error(
                        code,
                        Exception("ServerError")
                    )
                    else -> RetrofitResult.Error(
                        code,
                        Exception("UnExpectedError")
                    )
                }

                callback.onResponse(this@ResultCall, Response.success(result))
            }

            override fun onFailure(call: Call<T>, t: Throwable) {
                val result = if (t is IOException) {
                    RetrofitResult.Error(
                        0,
                        Exception("NetworkError")
                    )
                } else {
                    RetrofitResult.Error(
                        0,
                        t as Exception
                    )
                }

                callback.onResponse(this@ResultCall, Response.success(result))
            }
        })

    override fun cloneImpl() = ResultCall(proxy.clone())
}

class ResultAdapter(
    private val type: Type
) : CallAdapter<Type, Call<RetrofitResult<Type>>> {
    override fun responseType() = type
    override fun adapt(call: Call<Type>): Call<RetrofitResult<Type>> =
        ResultCall(call)
}

class MyCallAdapterFactory : CallAdapter.Factory() {
    override fun get(
        returnType: Type,
        annotations: Array<Annotation>,
        retrofit: Retrofit
    ) = when (getRawType(returnType)) {
        Call::class.java -> {
            val callType = getParameterUpperBound(0, returnType as ParameterizedType)
            when (getRawType(callType)) {
                RetrofitResult::class.java -> {
                    val resultType = getParameterUpperBound(0, callType as ParameterizedType)
                    ResultAdapter(resultType)
                }
                else -> null
            }
        }
        else -> null
    }
}