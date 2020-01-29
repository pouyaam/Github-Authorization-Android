package com.mydigipay.challenge.repository.token

import android.content.SharedPreferences

private const val TOKEN = "TOKEN"

class TokenRepositoryImpl(private val sharedPreferences: SharedPreferences) : TokenRepository {
    override suspend fun saveToken(token: String) =
        sharedPreferences.edit().apply { putString(TOKEN, token) }.apply()

    override suspend fun readToken(): String = sharedPreferences.getString(TOKEN, "") ?: ""
}