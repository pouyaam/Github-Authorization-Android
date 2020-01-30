package com.mydigipay.challenge.presentation

import androidx.lifecycle.ViewModel
import com.mydigipay.challenge.domain.repositories.token.TokenRepository

class MainViewModel(private val tokenRepository: TokenRepository) : ViewModel() {

    fun readToekn() = tokenRepository.readAccessToken()
}