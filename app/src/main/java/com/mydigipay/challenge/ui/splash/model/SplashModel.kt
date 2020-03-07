package com.mydigipay.challenge.ui.splash.model

import com.mydigipay.challenge.dataaccess.TokenDataSource

class SplashModel(
    private val repository: TokenDataSource
) {
    fun isUserLogin() = repository.isUserLogin()
}