package com.mydigipay.challenge.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.github.mohammadsianaki.core.toplevel.awaitIO
import com.mydigipay.challenge.domain.repositories.token.TokenRepository
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
            }
        }
    }

    fun saveToken(accessToken: String) {
        viewModelScope.launch {
            awaitIO {
                awaitIO { tokenRepository.saveToken(accessToken) }
            }
        }
    }

    suspend fun getToken(): String = tokenRepository.readToken()
}