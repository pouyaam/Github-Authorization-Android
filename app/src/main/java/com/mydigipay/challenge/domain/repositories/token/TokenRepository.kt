package com.mydigipay.challenge.domain.repositories.token

interface TokenRepository {
    suspend fun saveToken(token: String)
    suspend fun readToken(): String
}