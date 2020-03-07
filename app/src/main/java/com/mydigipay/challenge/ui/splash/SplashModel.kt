package com.mydigipay.challenge.ui.splash

import com.mydigipay.challenge.base.BaseModel
import com.mydigipay.challenge.data.repository.TokenRepository

class SplashModel(private val tokenRepository: TokenRepository):BaseModel() {

    suspend fun isUserLogin() = tokenRepository.isUserLoggedIn()
}