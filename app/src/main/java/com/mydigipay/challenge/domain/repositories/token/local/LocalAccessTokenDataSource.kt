package com.mydigipay.challenge.domain.repositories.token.local

interface LocalAccessTokenDataSource {
    suspend fun saveToken(token: String)
}