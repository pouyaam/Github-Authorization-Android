package com.mydigipay.challenge.data.network

import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response
import java.io.IOException

class AuthInterceptor(private val token: String) : Interceptor {
    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response {
        val builder: Request.Builder = chain.request().newBuilder()

        token.takeIf { !it.isBlank() }?.let {
            builder.addHeader("Authorization", it)
        }
        builder.addHeader("Accept", "application/json")
        builder.addHeader("Content-Type", "application/json")

        return chain.proceed(builder.build())
    }
}