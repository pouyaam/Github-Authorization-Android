package com.mydigipay.challenge.ui.authorize.model

import com.mydigipay.challenge.dataaccess.TokenDataSource

class AuthorizeModel(
    private val repository: TokenDataSource
) {

    fun getLoginUrl() = repository.getLoginUrl()

    fun getToken(code: String) = repository.generateToken(code)

    fun saveToken(accessToken: String) = repository.saveToken(accessToken)

}