package com.mydigipay.challenge.dataaccess.local

import com.mydigipay.challenge.dataaccess.TokenDataSource
import com.mydigipay.challenge.dataaccess.clientId
import com.mydigipay.challenge.dataaccess.clientState
import com.mydigipay.challenge.dataaccess.redirectUrl

class TokenLocalDataSource : TokenDataSource {

    override fun isUserLogin() = throw IllegalStateException("bad request")

    override fun saveToken(token: String) = throw IllegalStateException("bad request")

    override fun getToken() = throw IllegalStateException("bad request")

    override fun getLoginUrl() =
        "https://github.com/login/oauth/authorize?client_id=$clientId&redirect_uri=$redirectUrl&scope=repo user&state=$clientState"

    override fun generateToken(code: String) = throw IllegalStateException("bad request")
}