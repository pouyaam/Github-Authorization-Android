package com.mydigipay.challenge.domain.repositories.token

interface LocalAccessTokenDataSource {
    suspend fun saveToken(token: String)
    suspend fun readToken(): String
}