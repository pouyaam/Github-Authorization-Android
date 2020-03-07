package com.mydigipay.challenge.data.repository

import com.mydigipay.challenge.data.network.ApiResult
import com.mydigipay.challenge.data.pojo.User

interface UserRepository {

    suspend fun getAuthenticatedUser(): ApiResult<User>
}