package com.mydigipay.challenge.authorization

import androidx.lifecycle.viewModelScope
import com.mydigipay.challenge.authorization.repository.oauth.AccessTokenDataSource
import com.mydigipay.challenge.authorization.repository.oauth.model.RequestAccessToken
import com.mydigipay.challenge.authorization.repository.token.TokenRepository
import com.mydigipay.challenge.core.Const
import com.mydigipay.challenge.core.SingleLiveEvent
import com.mydigipay.challenge.core.base.BaseViewModel
import com.mydigipay.challenge.core.network.RetrofitResult
import kotlinx.coroutines.launch

class AuthorizationViewModel(
    private val tokenRepository: TokenRepository,
    private val accessTokenDataSource: AccessTokenDataSource
) : BaseViewModel() {

    val loginUri = SingleLiveEvent<String>()
    val accessToken = SingleLiveEvent<String?>()
    val saveAccessTokenSuccess = SingleLiveEvent<Unit>()

    fun handleLoginUri(loginBaseUrl: String, clientId: String, redirectUrl: String) {
        loginUri.value =
            "$loginBaseUrl?client_id=${clientId}&redirect_uri=$redirectUrl&scope=repo&state=0"
    }

    fun getAccessToken(accessTokenRequest: RequestAccessToken) {
        viewModelScope.launch {
            val result = accessTokenDataSource.accessToken(accessTokenRequest)

            if (result is RetrofitResult.Success) {
                accessToken.value = result.data?.accessToken
            } else if (result is RetrofitResult.Error) {
                mError.value = result.e.message
            }
        }
    }

    fun saveAccessToken(accessToken: String) {
        viewModelScope.launch {
            tokenRepository.saveToken(accessToken)
            saveAccessTokenSuccess.call()
        }
    }
}