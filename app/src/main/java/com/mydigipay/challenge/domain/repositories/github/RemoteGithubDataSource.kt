package com.mydigipay.challenge.domain.repositories.github

interface RemoteGithubDataSource {
    suspend fun perormSearch(query: String)
}