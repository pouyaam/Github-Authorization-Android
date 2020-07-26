package com.mydigipay.challenge.presentation.viewmodel

import android.app.Application
import androidx.lifecycle.viewModelScope
import com.mydigipay.challenge.authorization.AccessTokenRepository
import com.mydigipay.challenge.model.Status
import com.mydigipay.challenge.presentation.design.MviViewModel
import com.mydigipay.challenge.presentation.viewstate.AccessTokenViewEffect
import com.mydigipay.challenge.presentation.viewstate.AccessTokenViewEvent
import com.mydigipay.challenge.presentation.viewstate.AccessTokenViewState
import com.mydigipay.challenge.presentation.viewstate.FetchStatus
import kotlinx.coroutines.launch
import java.lang.IllegalArgumentException
import javax.inject.Inject

class AccessTokenViewModel @Inject constructor(
    application: Application,
    private val accessTokenRepository: AccessTokenRepository
) : MviViewModel<AccessTokenViewState, AccessTokenViewEffect, AccessTokenViewEvent>(application) {

    init {
        viewState = AccessTokenViewState(fetchStatus = FetchStatus.NotFetched)
    }

    override fun process(viewEvent: AccessTokenViewEvent) {
        super.process(viewEvent)

        when (viewEvent) {
            AccessTokenViewEvent.AuthorizationButtonClicked -> authorizationButtonClicked()
            is AccessTokenViewEvent.AuthorizationCodeRetrieved -> retrieveAccessToken(viewEvent.authorizationCode)
        }
    }

    private fun authorizationButtonClicked() {
        viewEffect = AccessTokenViewEffect.RetrieveAuthorizationCode
    }

    private fun retrieveAccessToken(authorizationCode: String) {
        viewState = viewState.copy(fetchStatus = FetchStatus.Fetching)

        viewModelScope.launch {
            val result = accessTokenRepository.getAccessToken(
                "",
                "",
                authorizationCode,
                ""
            )

            when (result.status) {
                Status.ERROR -> {
                    viewState = viewState.copy(fetchStatus = FetchStatus.Fetched)
                    viewEffect = AccessTokenViewEffect.ShowToast(
                        message = result.error?.message ?: "Un-known API call error"
                    )
                }
                Status.SUCCESS -> {
                    viewState =
                        viewState.copy(fetchStatus = FetchStatus.Fetched, accessToken = result.data)
                }
                else -> throw IllegalArgumentException("Un-expected API call status")
            }
        }
    }
}
