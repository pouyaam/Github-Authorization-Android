package com.mydigipay.challenge.authorization.repository.token

interface TokenRepository {
    suspend fun saveToken(token: String)
    fun readToken(): String
}