package com.mydigipay.challenge.github

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mydigipay.challenge.core.model.fold
import com.mydigipay.challenge.core.toplevel.awaitIO
import com.mydigipay.challenge.network.oauth.RequestAccessToken
import com.mydigipay.challenge.repository.oauth.AccessTokenDataSource
import com.mydigipay.challenge.repository.token.TokenRepository
import kotlinx.coroutines.launch

class LoginUriViewModel(
    private val tokenRepository: TokenRepository,
    private val accessTokenDataSource: AccessTokenDataSource
) : ViewModel() {

    suspend fun fetchAccessToken(code: String) {
        viewModelScope.launch {
            awaitIO {
                accessTokenDataSource.accessToken(
                    RequestAccessToken(
                        CLIENT_ID,
                        CLIENT_SECRET,
                        code,
                        REDIRECT_URI,
                        "0"
                    )
                )
            }.fold(
                ifSuccess = {
                    saveToken(it.accessToken)
                },
                ifFailure = {

                }
            )
        }
    }

    suspend fun saveToken(accessToken: String) {
        viewModelScope.launch {
            awaitIO {
                awaitIO { tokenRepository.saveToken(accessToken) }
            }
        }
    }

    suspend fun getToken(): String = tokenRepository.readToken()
}