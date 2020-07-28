package com.mydigipay.challenge.framework.preference

import android.content.SharedPreferences
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import javax.inject.Inject

class TokenDaoImpl @Inject constructor(
    private val sharedPreferences: SharedPreferences
) : TokenDao {

    override fun saveAccessTokenAsync(token: String): Deferred<Unit> =
        CoroutineScope(Dispatchers.IO).async {
            sharedPreferences.edit().apply {
                putString(KEY_TOKEN, token)
            }.apply()
        }

    override fun readAccessTokenAsync(): Deferred<String> =
        CoroutineScope(Dispatchers.IO).async {
            sharedPreferences.getString(KEY_TOKEN, "") ?: ""
        }

    /**
     * SharedPreferences Keys
     */

    companion object {
        const val KEY_TOKEN = "KEY_TOKEN"
    }
}
