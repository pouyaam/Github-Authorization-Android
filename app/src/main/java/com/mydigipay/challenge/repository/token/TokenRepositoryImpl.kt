package com.mydigipay.challenge.repository.token

import android.content.SharedPreferences
import androidx.core.content.edit
import com.mydigipay.challenge.network.oauth.AccessTokenService
import com.mydigipay.challenge.network.oauth.RequestAccessToken
import kotlinx.coroutines.*

private const val TOKEN = "TOKEN"

class TokenRepositoryImpl(
    private val sharedPreferences: SharedPreferences,
    private val accessTokenService: AccessTokenService
) : TokenRepository {

    override suspend fun accessToken(requestAccessToken: RequestAccessToken) =
        accessTokenService.accessToken(requestAccessToken)

    override suspend fun saveToken(token: String)=
        withContext(Dispatchers.IO) {
            sharedPreferences.edit(commit = true) { putString(TOKEN, token) }
        }


    override suspend fun readToken()=
        withContext(Dispatchers.IO) { sharedPreferences.getString(TOKEN, "") ?: "" }
}