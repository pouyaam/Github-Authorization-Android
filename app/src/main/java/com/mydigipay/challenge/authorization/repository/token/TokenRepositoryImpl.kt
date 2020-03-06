package com.mydigipay.challenge.authorization.repository.token

import android.content.SharedPreferences
import com.mydigipay.challenge.core.extension.PreferenceHelper.get
import com.mydigipay.challenge.core.extension.PreferenceHelper.set

private const val TOKEN = "TOKEN"

class TokenRepositoryImpl(private val sharedPreferences: SharedPreferences) : TokenRepository {
    override suspend fun saveToken(token: String) {
        sharedPreferences[TOKEN] = token
    }

    override fun readToken(): String = sharedPreferences[TOKEN] ?: ""
}