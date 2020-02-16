package com.mydigipay.challenge.ui.commits

import com.mydigipay.challenge.base.ViewState
import com.mydigipay.challenge.network.model.commit.CommitWrapper

data class CommitViewState(
    val isLoading: Boolean = false,
    val commits: List<CommitWrapper> = listOf(),
    val error: Throwable? = null
) : ViewState