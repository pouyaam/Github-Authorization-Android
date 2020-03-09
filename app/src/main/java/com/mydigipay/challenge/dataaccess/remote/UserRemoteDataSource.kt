package com.mydigipay.challenge.dataaccess.remote

import com.mydigipay.challenge.dataaccess.UserDataSource

class UserRemoteDataSource(
    private val api: GitApiService
) : UserDataSource {
    override fun getUser(token: String) = api.getAuthenticatedUser(token)
}