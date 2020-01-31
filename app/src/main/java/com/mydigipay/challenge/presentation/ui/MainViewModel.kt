package com.mydigipay.challenge.presentation.ui

import androidx.lifecycle.ViewModel
import com.mydigipay.challenge.domain.repositories.token.TokenRepository

class MainViewModel(private val tokenRepository: TokenRepository) : ViewModel() {

    fun readToken() = tokenRepository.readAccessToken()
}