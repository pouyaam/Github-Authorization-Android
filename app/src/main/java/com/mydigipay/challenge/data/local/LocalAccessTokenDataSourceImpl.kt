package com.mydigipay.challenge.data.local

import android.content.SharedPreferences
import com.github.mohammadsianaki.core.toplevel.awaitIO
import com.mydigipay.challenge.domain.repositories.token.LocalAccessTokenDataSource

private const val TOKEN = "TOKEN"

class LocalAccessTokenDataSourceImpl(private val sharedPreferences: SharedPreferences) :
    LocalAccessTokenDataSource {
    override suspend fun saveToken(token: String) =
        awaitIO { sharedPreferences.edit().apply { putString(TOKEN, token) }.apply() }

    override suspend fun readToken(): String =
        awaitIO { sharedPreferences.getString(TOKEN, "") ?: "" }
}