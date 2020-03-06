package com.mydigipay.challenge.data.repository.user

import com.mydigipay.challenge.data.network.ApiResult
import com.mydigipay.challenge.data.network.safeApiCall
import com.mydigipay.challenge.data.network.user.UserService

class UserRepositoryImpl(
    private val userService: UserService
) : UserRepository {

    override suspend fun getAuthenticatedUser() = safeApiCall {
        return@safeApiCall ApiResult.Success(
            userService.getAuthenticatedUser()
        )
    }

}