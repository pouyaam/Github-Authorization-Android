package com.mydigipay.challenge.dataaccess.cache

import android.content.SharedPreferences
import com.mydigipay.challenge.dataaccess.TokenDataSource
import com.mydigipay.challenge.dataaccess.isUserLoginKey
import com.mydigipay.challenge.dataaccess.userTokenKey


class TokenCacheDataSource(
    private val sharePreferences: SharedPreferences
) : TokenDataSource {

    override fun isUserLogin() = sharePreferences.getBoolean(isUserLoginKey, false)

    override fun saveToken(token: String) {
        sharePreferences
            .edit()
            .putString(userTokenKey, token)
            .apply()
    }

    override fun getToken() = sharePreferences.getString(userTokenKey, "") ?: ""

    override fun getLoginURL() = throw IllegalStateException("bad request")

    override fun generateToken(code: String) = throw IllegalStateException("bad request")
}