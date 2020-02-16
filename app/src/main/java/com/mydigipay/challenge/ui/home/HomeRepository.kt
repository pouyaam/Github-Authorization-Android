package com.mydigipay.challenge.ui.home

import com.mydigipay.challenge.ui.home.RepositoryResult

interface HomeRepository {
    var code: String?
    suspend fun saveToken(token: String)
    suspend fun getRepositories(code: String): RepositoryResult
    suspend fun getToken(code: String): Boolean
}