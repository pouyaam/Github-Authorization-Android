package com.mydigipay.challenge.domain.usecase

import com.mydigipay.challenge.domain.repository.TokenRepository
import javax.inject.Inject

class AccessTokenUseCase @Inject constructor(private val tokenRepository: TokenRepository) {

    fun fetchAccessToken(code: String) {
        tokenRepository.fetchAccessToken(code)
    }
}