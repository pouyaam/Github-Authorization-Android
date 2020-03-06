package com.mydigipay.challenge.ui.home

import com.mydigipay.challenge.base.BaseModel
import com.mydigipay.challenge.data.repository.TokenRepository

class HomeModel(private val tokenRepository: TokenRepository) : BaseModel() {

    val getLoginUrl = tokenRepository.getLoginUrl()

    suspend fun accessToken(code: String) = tokenRepository.accessToken(code)

}