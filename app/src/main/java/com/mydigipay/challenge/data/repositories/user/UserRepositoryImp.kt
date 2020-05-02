package com.mydigipay.challenge.data.repositories.user

import com.mydigipay.challenge.data.models.RequestAccessToken
import com.mydigipay.challenge.data.network.services.UserService
import com.mydigipay.challenge.data.repositories.fetchFromNetwork
import com.mydigipay.challenge.ui.authorize.CLIENT_ID
import com.mydigipay.challenge.ui.authorize.CLIENT_SECRET
import com.mydigipay.challenge.ui.authorize.REDIRECT_URI
import com.mydigipay.challenge.ui.authorize.STATE

class UserRepositoryImp(private val userService: UserService) : UserRepository {

    override suspend fun getAccessToken(code: String) = fetchFromNetwork(
        {
            userService.getAccessToken(
                RequestAccessToken(
                    CLIENT_ID,
                    CLIENT_SECRET, code,
                    REDIRECT_URI,
                    STATE
                )
            )
        }
    )

    override suspend fun profile(token: String?) =
        fetchFromNetwork({ userService.profile(token) })

    override suspend fun repos(page: Int) = fetchFromNetwork({ userService.repos(page) })
}