package com.mydigipay.challenge.data.network.interceptor

import com.mydigipay.challenge.data.network.ApiException
import com.mydigipay.challenge.data.persist.SharedPrefWrapper
import okhttp3.Interceptor
import okhttp3.Response

const val AUTH_STATE = "Auth-State"
private const val AUTHORIZATION = "Authorization"

class TokenInterceptor(
    private val sharedPrefWrapper: SharedPrefWrapper
) : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        var request = chain.request()
        val authState = request.header(AUTH_STATE)
        authState?.let {state ->
            when (state) {
                AuthState.OPTIONAL.toString() -> {
                    sharedPrefWrapper.readToken().takeIf { it.isNotEmpty() }?.let {
                        request = request.newBuilder().addHeader(AUTHORIZATION, it).build()
                    }
                }
                AuthState.FORCE.toString() -> {
                    sharedPrefWrapper.readToken().takeIf { it.isNotEmpty() }?.let {
                        request = request.newBuilder().addHeader(AUTHORIZATION, it).build()
                    } ?: throw ApiException("Endpoint needs $AUTHORIZATION header but token is empty")
                }
                else -> throw ApiException("Not supported ${AuthState::class.java.simpleName} type")
            }
        }
        return chain.proceed(request)
    }
}

enum class AuthState {
    OPTIONAL {
        override fun toString() = "Optional"
    },
    FORCE {
        override fun toString() = "Force"
    }
}