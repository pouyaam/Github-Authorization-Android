package com.mydigipay.challenge.domain.usecase

import com.mydigipay.challenge.domain.repository.TokenRepository
import io.reactivex.Single
import javax.inject.Inject

class AuthCheckingUseCase @Inject constructor(private val tokenRepository: TokenRepository) {

    fun isUserAuthorized(): Boolean {
        return !tokenRepository.readToken().isBlank()
    }
}