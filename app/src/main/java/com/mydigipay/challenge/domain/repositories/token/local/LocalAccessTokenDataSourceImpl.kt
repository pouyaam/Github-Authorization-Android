package com.mydigipay.challenge.domain.repositories.token.local

import android.content.SharedPreferences

private const val TOKEN = "TOKEN"

class LocalAccessTokenDataSourceImpl(private val sharedPreferences: SharedPreferences) :
    LocalAccessTokenDataSource {
    override suspend fun saveToken(token: String) =
        sharedPreferences.edit().apply { putString(TOKEN, token) }.apply()

    suspend fun readToken(): String = sharedPreferences.getString(TOKEN, "") ?: ""
}