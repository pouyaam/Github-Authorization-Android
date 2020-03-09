package com.mydigipay.challenge.ui.profile.model

import com.mydigipay.challenge.dataaccess.TokenDataSource
import com.mydigipay.challenge.dataaccess.UserDataSource

class ProfileModel(
    private val tokenRepository: TokenDataSource,
    private val userRepository: UserDataSource
) {
    fun getUser() = userRepository.getUser(
        "Bearer ${tokenRepository.getToken()}"
    )
}