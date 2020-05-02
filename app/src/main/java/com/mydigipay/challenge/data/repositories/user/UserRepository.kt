package com.mydigipay.challenge.data.repositories.user

import com.mydigipay.challenge.data.models.ResponseAccessToken
import com.mydigipay.challenge.data.models.User
import com.mydigipay.challenge.data.repositories.ApiResult

interface UserRepository {
    suspend fun getAccessToken(code: String): ApiResult<ResponseAccessToken>
    suspend fun profile(token: String? = null): ApiResult<User>
}