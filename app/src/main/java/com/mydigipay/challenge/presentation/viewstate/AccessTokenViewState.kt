package com.mydigipay.challenge.presentation.viewstate

import android.content.Intent
import com.mydigipay.challenge.authorization.AccessToken
import com.mydigipay.challenge.presentation.model.ListItem

data class AccessTokenViewState(
    val fetchStatus: AccessTokenFetchStatus
)

sealed class AccessTokenViewEffect {
    data class ShowToast(val message: String) : AccessTokenViewEffect()
    data class ShowSnackBar(val message: String) : AccessTokenViewEffect()
    data class ShowDialog(val message: String) : AccessTokenViewEffect()
    object StartAuthorizationAction : AccessTokenViewEffect()
    object NavigateToGithubRepos : AccessTokenViewEffect()
}

sealed class AccessTokenViewEvent {
    data class NewIntentReceived(val intent: Intent) : AccessTokenViewEvent()
    object AuthorizationButtonClicked : AccessTokenViewEvent()
}

sealed class AccessTokenFetchStatus {
    object Fetching : AccessTokenFetchStatus()
    object Fetched : AccessTokenFetchStatus()
    object NotFetched : AccessTokenFetchStatus()
}
