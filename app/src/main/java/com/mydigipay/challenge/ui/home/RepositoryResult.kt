package com.mydigipay.challenge.ui.home

import com.mydigipay.challenge.base.Result
import com.mydigipay.challenge.network.repository.Repository

sealed class RepositoryResult : Result {
    object Loading : RepositoryResult()
    data class Success(val data: List<Repository>) : RepositoryResult()
    data class Failure(val error: Throwable) : RepositoryResult()
}