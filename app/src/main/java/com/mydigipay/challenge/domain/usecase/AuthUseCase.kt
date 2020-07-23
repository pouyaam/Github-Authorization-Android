package com.mydigipay.challenge.domain.usecase

import com.mydigipay.challenge.domain.repository.TokenRepository
import io.reactivex.Completable
import io.reactivex.Single
import javax.inject.Inject

class AuthUseCase @Inject constructor(private val tokenRepository: TokenRepository) {

    fun isUserAuthorized(): Boolean {
        return !tokenRepository.readToken().isBlank()
    }

    fun fetchAccessToken(code: String): Completable {
        return tokenRepository.fetchAccessToken(code)
    }
}