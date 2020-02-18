package com.mydigipay.challenge.ui.profile

import com.mydigipay.challenge.base.ViewState
import com.mydigipay.challenge.network.model.repository.Repository
import com.mydigipay.challenge.network.model.user.User

data class ProfileViewState(
    val isLoading: Boolean = false,
    val user: User = User.EMPTY,
    val requiredCode: Boolean = false,
    val error: Throwable? = null
) : ViewState