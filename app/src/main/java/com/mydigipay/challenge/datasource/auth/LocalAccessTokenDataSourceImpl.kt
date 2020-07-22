package com.mydigipay.challenge.datasource.auth

import android.content.SharedPreferences
import com.mydigipay.challenge.data.datasource.auth.LocalAccessTokenDataSource
import javax.inject.Inject

class LocalAccessTokenDataSourceImpl @Inject constructor(private val sharedPreferences: SharedPreferences) :
    LocalAccessTokenDataSource {

    private val TOKEN = "TOKEN"

    override fun readToken(): String {
        return sharedPreferences.getString(TOKEN, "") ?: ""
    }
}