package com.mydigipay.challenge.presentation.viewmodel

import android.app.Application
import android.content.Intent
import androidx.lifecycle.viewModelScope
import com.mydigipay.challenge.CLIENT_ID
import com.mydigipay.challenge.CLIENT_SECRET
import com.mydigipay.challenge.REDIRECT_URI
import com.mydigipay.challenge.authorization.GetAccessTokenUseCase
import com.mydigipay.challenge.authorization.SaveAccessTokenUseCase
import com.mydigipay.challenge.model.Status
import com.mydigipay.challenge.presentation.design.MviViewModel
import com.mydigipay.challenge.presentation.viewstate.AccessTokenViewEffect
import com.mydigipay.challenge.presentation.viewstate.AccessTokenViewEvent
import com.mydigipay.challenge.presentation.viewstate.AccessTokenViewState
import com.mydigipay.challenge.presentation.viewstate.FetchStatus
import kotlinx.coroutines.launch
import javax.inject.Inject

class AccessTokenViewModel @Inject constructor(
    application: Application,
    private val getAccessTokenUseCase: GetAccessTokenUseCase,
    private val saveAccessTokenUseCase: SaveAccessTokenUseCase
) : MviViewModel<AccessTokenViewState, AccessTokenViewEffect, AccessTokenViewEvent>(application) {

    init {
        viewState = AccessTokenViewState(fetchStatus = FetchStatus.NotFetched)
    }

    override fun process(viewEvent: AccessTokenViewEvent) {
        super.process(viewEvent)

        when (viewEvent) {
            AccessTokenViewEvent.AuthorizationButtonClicked -> authorizationButtonClicked()
            is AccessTokenViewEvent.NewIntentReceived -> processNewIntent(viewEvent.intent)
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
        viewState = viewState.copy(fetchStatus = FetchStatus.Fetching)

        viewModelScope.launch {
            val result = getAccessTokenUseCase(
                CLIENT_ID,
                CLIENT_SECRET,
                authorizationCode,
                REDIRECT_URI
            )

            when (result.status) {
                Status.ERROR -> {
                    viewState = viewState.copy(fetchStatus = FetchStatus.NotFetched)

                    viewEffect = AccessTokenViewEffect.ShowToast(
                        message = result.error?.message ?: "Un-known API call error"
                    )
                }
                Status.SUCCESS -> {
                    saveAccessToken(result.data!!.accessToken)

                    viewState =
                        viewState.copy(fetchStatus = FetchStatus.Fetched, accessToken = result.data)
                }
                else -> throw IllegalArgumentException("Un-expected API call status")
            }
        }
    }

    private fun saveAccessToken(token: String) {
        viewModelScope.launch {
            saveAccessTokenUseCase(token)
        }
    }
}
