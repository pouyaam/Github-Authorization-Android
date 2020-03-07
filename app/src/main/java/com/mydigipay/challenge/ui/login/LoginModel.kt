package com.mydigipay.challenge.ui.login

import com.mydigipay.challenge.base.BaseModel
import com.mydigipay.challenge.data.repository.TokenRepository

class LoginModel(private val tokenRepository: TokenRepository) : BaseModel() {

    val getLoginUrl = tokenRepository.getLoginUrl()

    suspend fun accessToken(code: String) = tokenRepository.accessToken(code)

}