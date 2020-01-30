package com.mydigipay.challenge.data.api

import com.mydigipay.challenge.domain.repositories.token.TokenRepository
import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response

const val AUTH_TOKEN_KEY = "Authorization"

class TokenInterceptor(private val tokenRepository: TokenRepository) : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val originalRequest = chain.request()
        val accessToken = tokenRepository.readAccessToken()

        if (alreadyHasAuthorizationHeader(originalRequest)) {
            return chain.proceed(originalRequest)
        }

        val requestBuilder = originalRequest.newBuilder()
            .header(AUTH_TOKEN_KEY, "Bearer $accessToken")
            .method(originalRequest.method, originalRequest.body)

        return chain.proceed(requestBuilder.build())
    }

    private fun alreadyHasAuthorizationHeader(request: Request): Boolean {
        return !request.header(AUTH_TOKEN_KEY).isNullOrEmpty()
    }
}