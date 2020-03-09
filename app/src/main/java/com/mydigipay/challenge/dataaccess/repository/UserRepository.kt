package com.mydigipay.challenge.dataaccess.repository

import com.mydigipay.challenge.dataaccess.UserDataSource

class UserRepository(
    private val remote: UserDataSource
) : UserDataSource {
    override fun getUser(token: String) = remote.getUser(token)
}