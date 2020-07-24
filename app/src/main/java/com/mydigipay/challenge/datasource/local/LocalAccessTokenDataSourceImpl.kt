package com.mydigipay.challenge.datasource.local

import android.content.SharedPreferences
import com.mydigipay.challenge.data.datasource.local.LocalAccessTokenDataSource
import io.reactivex.Completable
import javax.inject.Inject

const val TOKEN_PREF_KEY = "TOKEN"

class LocalAccessTokenDataSourceImpl @Inject constructor(private val sharedPreferences: SharedPreferences) :
    LocalAccessTokenDataSource {


    override fun readToken(): String {
        return sharedPreferences.getString(TOKEN_PREF_KEY, "") ?: ""
    }

    override fun saveToken(token: String): Completable {
        return Completable.create { emitter ->
            sharedPreferences.edit().apply { putString(TOKEN_PREF_KEY, token) }.apply()
            emitter.onComplete()
        }
    }
}