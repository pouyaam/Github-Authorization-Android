package com.mydigipay.challenge.data.repository

import com.mydigipay.challenge.data.network.ApiResult

interface TokenRepository {
    suspend fun readToken(): String
    suspend fun accessToken(code: String): ApiResult<Unit>
    fun getLoginUrl(): String
    suspend fun isUserLoggedIn(): Boolean
}