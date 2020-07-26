package com.mydigipay.challenge.presentation.viewstate

import android.content.Intent
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
    object StartAuthorizationAction : AccessTokenViewEffect()
}

sealed class AccessTokenViewEvent {
    object AuthorizationButtonClicked : AccessTokenViewEvent()
    data class NewIntentReceived(val intent: Intent) : AccessTokenViewEvent()
}

sealed class FetchStatus {
    object Fetching : FetchStatus()
    object Fetched : FetchStatus()
    object NotFetched : FetchStatus()
}
