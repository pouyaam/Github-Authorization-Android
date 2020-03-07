package com.mydigipay.challenge.data.repository

import com.mydigipay.challenge.data.network.ApiResult
import com.mydigipay.challenge.data.network.api.UserApi
import com.mydigipay.challenge.data.network.safeApiCall

class UserRepositoryImpl(private val userApi: UserApi) : UserRepository {

    override suspend fun getAuthenticatedUser() = safeApiCall {
        return@safeApiCall ApiResult.Success(
            userApi.getAuthenticatedUser()
        )
    }

}