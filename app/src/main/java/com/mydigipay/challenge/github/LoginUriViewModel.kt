package com.mydigipay.challenge.github

import androidx.lifecycle.ViewModel
import com.mydigipay.challenge.network.oauth.RequestAccessToken
import com.mydigipay.challenge.network.oauth.ResponseAccessToken
import com.mydigipay.challenge.repository.oauth.AccessTokenDataSource
import com.mydigipay.challenge.repository.token.TokenRepository
import kotlinx.coroutines.Deferred

class LoginUriViewModel(
    private val tokenRepository: TokenRepository,
    private val accessTokenDataSource: AccessTokenDataSource
) : ViewModel() {

    fun fetchAccescToken(code: String): Deferred<ResponseAccessToken> {
        return accessTokenDataSource.accessToken(
            RequestAccessToken(
                CLIENT_ID,
                CLIENT_SECRET,
                code,
                REDIRECT_URI,
                "0"
            )
        )
    }

    fun saveToken(accessToken: String): Deferred<Unit> {
        return tokenRepository.saveToken(accessToken)
    }

    fun getToken(): Deferred<String> {
        return tokenRepository.readToken()
    }
}