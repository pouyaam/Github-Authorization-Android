package com.mydigipay.challenge.data.local

import android.content.SharedPreferences
import com.mydigipay.challenge.domain.repositories.token.LocalAccessTokenDataSource

private const val TOKEN = "TOKEN"

class LocalAccessTokenDataSourceImpl(private val sharedPreferences: SharedPreferences) :
    LocalAccessTokenDataSource {
    override suspend fun saveToken(token: String) =
        sharedPreferences.edit().apply { putString(TOKEN, token) }.apply()

    suspend fun readToken(): String = sharedPreferences.getString(TOKEN, "") ?: ""
}