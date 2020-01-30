package com.mydigipay.challenge.domain.repositories.github

interface GithubRepository {
    suspend fun performSearch(query: String)
}