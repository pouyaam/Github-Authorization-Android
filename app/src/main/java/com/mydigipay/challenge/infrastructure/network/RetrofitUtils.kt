package com.mydigipay.challenge.infrastructure.network

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class RetrofitUtils {

    companion object {
        const val END_POINT = "https://api.github.com/"
    }

    private val defaultOkHttpRequestTimeoutSeconds = 50L
    private val defaultOkHttpResponseTimeoutSeconds = 50L
    private val defaultOkHttpTimeoutSeconds = 50L

    fun getRetrofit(): Retrofit {
        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BODY

        val httpClient = OkHttpClient.Builder()
            .addInterceptor(interceptor)
            .readTimeout(defaultOkHttpRequestTimeoutSeconds, TimeUnit.SECONDS)
            .writeTimeout(defaultOkHttpResponseTimeoutSeconds, TimeUnit.SECONDS)
            .connectTimeout(defaultOkHttpTimeoutSeconds, TimeUnit.SECONDS)
            .build()

        return Retrofit.Builder()
            .baseUrl(END_POINT)
            .client(httpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()
    }

}