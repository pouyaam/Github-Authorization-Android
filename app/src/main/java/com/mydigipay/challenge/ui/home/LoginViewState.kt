package com.mydigipay.challenge.ui.home

import com.google.gson.annotations.SerializedName
import com.mydigipay.challenge.base.ViewState
import com.mydigipay.challenge.network.repository.Repository

data class LoginViewState(
    val isLoading: Boolean = false,
    @SerializedName("results")
    val repositories: List<Repository> = listOf(),
    val requiredCode: Boolean = false,
    val error: Throwable? = null
) : ViewState