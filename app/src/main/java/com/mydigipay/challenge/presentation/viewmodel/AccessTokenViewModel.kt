package com.mydigipay.challenge.presentation.viewmodel

import android.app.Application
import android.content.Intent
import androidx.lifecycle.viewModelScope
import com.mydigipay.challenge.CLIENT_ID
import com.mydigipay.challenge.CLIENT_SECRET
import com.mydigipay.challenge.REDIRECT_URI
import com.mydigipay.challenge.authorization.AccessToken
import com.mydigipay.challenge.authorization.GetAccessTokenUseCase
import com.mydigipay.challenge.authorization.SaveAccessTokenUseCase
import com.mydigipay.challenge.model.Status
import com.mydigipay.challenge.presentation.design.MviViewModel
import com.mydigipay.challenge.presentation.viewstate.*
import kotlinx.coroutines.launch
import javax.inject.Inject

class AccessTokenViewModel @Inject constructor(
    application: Application,
    private val getAccessTokenUseCase: GetAccessTokenUseCase,
    private val saveAccessTokenUseCase: SaveAccessTokenUseCase
) : MviViewModel<AccessTokenViewState, AccessTokenViewEffect, AccessTokenViewEvent>(application) {

    init {
        viewState = AccessTokenViewState(fetchStatus = AccessTokenFetchStatus.NotFetched)
    }

    override fun process(viewEvent: AccessTokenViewEvent) {
        super.process(viewEvent)

        when (viewEvent) {
            is AccessTokenViewEvent.NewIntentReceived -> processNewIntent(viewEvent.intent)
            AccessTokenViewEvent.AuthorizationButtonClicked -> authorizationButtonClicked()
        }
    }

    private fun processNewIntent(intent: Intent) {
        if (intent.action == Intent.ACTION_VIEW) {
            val uri = intent.data
            val code = uri?.getQueryParameter("code")
            code?.let {
                retrieveAccessToken(it)
            } ?: run {
                viewEffect = AccessTokenViewEffect.ShowToast(
                    message = "Unable to retrieve authorization code"
                )
            }
        }
    }

    private fun authorizationButtonClicked() {
        viewEffect = AccessTokenViewEffect.StartAuthorizationAction
    }

    private fun retrieveAccessToken(authorizationCode: String) {
        viewState = viewState.copy(fetchStatus = AccessTokenFetchStatus.Fetching)

        viewModelScope.launch {
            val result = getAccessTokenUseCase(
                CLIENT_ID,
                CLIENT_SECRET,
                authorizationCode,
                REDIRECT_URI
            )

            when (result.status) {
                Status.ERROR -> {
                    viewState = viewState.copy(fetchStatus = AccessTokenFetchStatus.NotFetched)

                    viewEffect = AccessTokenViewEffect.ShowToast(
                        message = result.error?.message ?: "Un-known API call error"
                    )
                }
                Status.SUCCESS -> {
                    saveAccessToken(result.data!!)

                    viewState =
                        viewState.copy(fetchStatus = AccessTokenFetchStatus.Fetched)

                    viewEffect = AccessTokenViewEffect.NavigateToGithubRepos
                }
                else -> throw IllegalArgumentException("Un-expected API call status")
            }
        }
    }

    private fun saveAccessToken(accessToken: AccessToken) {
        viewModelScope.launch {
            saveAccessTokenUseCase("${accessToken.tokenType} ${accessToken.accessToken}")
        }
    }
}
