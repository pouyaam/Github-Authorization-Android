package com.mydigipay.challenge.repository.token

import android.content.SharedPreferences
import androidx.core.content.edit
import kotlinx.coroutines.*

private const val TOKEN = "TOKEN"

class TokenRepositoryImpl(private val sharedPreferences: SharedPreferences) : TokenRepository {

    override suspend fun saveToken(token: String)=
        withContext(Dispatchers.IO) {
            sharedPreferences.edit(commit = true) { putString(TOKEN, token) }
        }


    override suspend fun readToken()=
        withContext(Dispatchers.IO) { sharedPreferences.getString(TOKEN, "") ?: "" }
}