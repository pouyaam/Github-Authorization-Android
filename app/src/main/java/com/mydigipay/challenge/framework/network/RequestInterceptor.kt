package com.mydigipay.challenge.framework.network

import com.mydigipay.challenge.CLIENT_ID
import com.mydigipay.challenge.CLIENT_SECRET
import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RequestInterceptor @Inject constructor() : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        var request = chain.request()

        val httpUrl = request.url.newBuilder()
            //.addQueryParameter(CLIENT_ID_QUERY, CLIENT_ID_VALUE)
            //.addQueryParameter(CLIENT_SECRET_QUERY, CLIENT_SECRET_VALUE)
            .build()

        request = request.newBuilder().url(httpUrl).build()

        return chain.proceed(request)
    }

    companion object {
        const val CLIENT_ID_QUERY = "client_id"
        const val CLIENT_ID_VALUE = CLIENT_ID
        const val CLIENT_SECRET_QUERY = "client_secret"
        const val CLIENT_SECRET_VALUE = CLIENT_SECRET
    }
}
