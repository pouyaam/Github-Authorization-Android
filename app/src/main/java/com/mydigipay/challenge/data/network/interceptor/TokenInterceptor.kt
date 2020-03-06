package com.mydigipay.challenge.data.network.interceptor

import com.mydigipay.challenge.data.network.ApiException
import com.mydigipay.challenge.data.persist.SharedPrefWrapper
import okhttp3.Interceptor
import okhttp3.Response

const val AUTH_STATE = "Auth-State"
const val AUTH_STATE_OPTIONAL = "Optional"
const val AUTH_STATE_FORCE = "Force"

private const val AUTHORIZATION = "Authorization"
private const val BEARER = "Bearer"

class TokenInterceptor(
    private val sharedPrefWrapper: SharedPrefWrapper
) : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        var request = chain.request()
        val authState = request.header(AUTH_STATE)
        authState?.let {state ->
            when (state) {
                AUTH_STATE_OPTIONAL -> {
                    sharedPrefWrapper.readToken().takeIf { it.isNotEmpty() }?.let {
                        request = request.newBuilder().addHeader(AUTHORIZATION, "$BEARER $it").build()
                    }
                }
                AUTH_STATE_FORCE -> {
                    sharedPrefWrapper.readToken().takeIf { it.isNotEmpty() }?.let {
                        request = request.newBuilder().addHeader(AUTHORIZATION, "$BEARER $it").build()
                    } ?: throw ApiException("Endpoint needs $AUTHORIZATION header but token is empty")
                }
                else -> throw ApiException("Not supported $AUTH_STATE type")
            }
        }
        return chain.proceed(request)
    }
}