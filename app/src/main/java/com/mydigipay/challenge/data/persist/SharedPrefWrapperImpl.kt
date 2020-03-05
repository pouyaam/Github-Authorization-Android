package com.mydigipay.challenge.data.persist

import android.content.SharedPreferences
import androidx.core.content.edit
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

private const val TOKEN = "TOKEN"

class SharedPrefWrapperImpl(
    private val sharedPreferences: SharedPreferences
) : SharedPrefWrapper {

    override suspend fun saveToken(token: String) =
        withContext(Dispatchers.IO) {
            sharedPreferences.edit(commit = true) {
                putString(
                    TOKEN,
                    token
                )
            }
        }

    override suspend fun readToken() =
        withContext(Dispatchers.IO) { sharedPreferences.getString(TOKEN, "") ?: "" }

    override suspend fun isUserLoggedIn() =
        withContext(Dispatchers.IO) { !sharedPreferences.getString(TOKEN, null).isNullOrBlank() }
}