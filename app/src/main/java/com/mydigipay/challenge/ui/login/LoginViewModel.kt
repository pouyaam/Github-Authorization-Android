package com.mydigipay.challenge.ui.login

import android.content.Intent
import android.net.Uri
import com.mydigipay.challenge.base.BaseViewModel
import com.mydigipay.challenge.data.repository.token.TokenRepository

class LoginViewModel(
    private val tokenRepository: TokenRepository
) : BaseViewModel() {

    fun onLoginClicked() {
        openIntent(action = Intent.ACTION_VIEW, uri = Uri.parse(tokenRepository.getLoginUrl()))
    }

}