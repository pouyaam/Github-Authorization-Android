package com.mydigipay.challenge.ui.splash

import com.mydigipay.challenge.R
import com.mydigipay.challenge.base.BaseViewModel
import com.mydigipay.challenge.data.repository.token.TokenRepository
import com.mydigipay.challenge.util.ktx.launch

class SplashViewModel(
    private val tokenRepository: TokenRepository
) : BaseViewModel() {

    init {
        checkDestination()
    }

    private fun checkDestination() = launch {
        if (tokenRepository.isUserLoggedIn()) showSnackBar(R.string.login_successful)
        else navigateTo(SplashFragmentDirections.actionSplashToLogin())
    }

}