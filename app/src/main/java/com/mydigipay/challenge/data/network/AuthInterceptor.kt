package com.mydigipay.challenge.data.network

import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response
import java.io.IOException

const val AUTHENTICATE_TOKEN = "AUTHENTICATE_TOKEN"

class AuthInterceptor(private var token: String) : Interceptor {
    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()

        request.header(AUTHENTICATE_TOKEN)?.let {
            token = it
        }

        val builder: Request.Builder = request.newBuilder()

        token.takeIf { it != "" }?.let {
            builder.addHeader("Authorization", "bearer $it")
        }
        builder.addHeader("Accept", "application/json")
        builder.addHeader("Content-Type", "application/json")

        return chain.proceed(builder.build())
    }
}