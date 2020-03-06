package com.mydigipay.challenge.data.repository.user

import com.mydigipay.challenge.data.model.User
import com.mydigipay.challenge.data.network.ApiResult

interface UserRepository {

    suspend fun getAuthenticatedUser(): ApiResult<User>
}