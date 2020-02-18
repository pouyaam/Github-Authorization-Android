package com.mydigipay.challenge.ui.home

import com.mydigipay.challenge.base.ViewState
import com.mydigipay.challenge.network.model.repository.Repository

data class HomeViewState(
    val isLoading: Boolean = false,
    val repositories: List<Repository> = listOf(),
    val requiredCode: Boolean = false,
    val error: Throwable? = null
) : ViewState