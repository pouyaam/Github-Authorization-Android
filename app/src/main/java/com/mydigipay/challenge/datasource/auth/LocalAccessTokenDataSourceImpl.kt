package com.mydigipay.challenge.datasource.auth

import android.content.SharedPreferences
import com.mydigipay.challenge.data.datasource.auth.LocalAccessTokenDataSource
import io.reactivex.Completable
import javax.inject.Inject

const val TOKEN = "TOKEN"

class LocalAccessTokenDataSourceImpl @Inject constructor(private val sharedPreferences: SharedPreferences) :
    LocalAccessTokenDataSource {


    override fun readToken(): String {
        return sharedPreferences.getString(TOKEN, "") ?: ""
    }

    override fun saveToken(token: String): Completable {
        return Completable.create { emitter ->
            sharedPreferences.edit().apply { putString(TOKEN, token) }.apply()
            emitter.onComplete()
        }
    }
}