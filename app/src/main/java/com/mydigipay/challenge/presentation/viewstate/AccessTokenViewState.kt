package com.mydigipay.challenge.presentation.viewstate

import com.mydigipay.challenge.authorization.AccessToken

data class AccessTokenViewState(
    val fetchStatus: FetchStatus,
    val errorMessage: String? = null,
    val accessToken: AccessToken? = null
)

sealed class AccessTokenViewEffect {
    data class ShowToast(val message: String) : AccessTokenViewEffect()
    data class ShowSnackBar(val message: String) : AccessTokenViewEffect()
    data class ShowDialog(val message: String) : AccessTokenViewEffect()
    object RetrieveAuthorizationCode : AccessTokenViewEffect()
}

sealed class AccessTokenViewEvent {
    object AuthorizationButtonClicked : AccessTokenViewEvent()
    data class AuthorizationCodeRetrieved(val authorizationCode: String) : AccessTokenViewEvent()
}

sealed class FetchStatus {
    object Fetching : FetchStatus()
    object Fetched : FetchStatus()
    object NotFetched : FetchStatus()
}
