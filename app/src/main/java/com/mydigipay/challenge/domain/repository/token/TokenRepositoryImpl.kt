package com.mydigipay.challenge.domain.repository.token

import android.content.SharedPreferences
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async

private const val TOKEN = "TOKEN"

class TokenRepositoryImpl(private val sharedPreferences: SharedPreferences) : TokenRepository {
    override fun saveToken(token: String): Deferred<Unit> =
        CoroutineScope(Dispatchers.IO).async { sharedPreferences.edit().apply { putString(TOKEN, token) }.apply() }


    override fun readToken(): Deferred<String> =
        CoroutineScope(Dispatchers.IO).async { sharedPreferences.getString(TOKEN, "") ?: "" }
}