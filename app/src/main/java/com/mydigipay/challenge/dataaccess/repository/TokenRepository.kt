package com.mydigipay.challenge.dataaccess.repository

import com.mydigipay.challenge.dataaccess.TokenDataSource

class TokenRepository(
    private val local: TokenDataSource,
    private val cache: TokenDataSource,
    private val remote: TokenDataSource
) : TokenDataSource {
    override fun isUserLogin() = cache.isUserLogin()

    override fun saveToken(token: String) = cache.saveToken(token)

    override fun getToken() = cache.getToken()

    override fun getLoginURL() = local.getLoginURL()

    override fun generateToken(code: String) = remote.generateToken(code)
}