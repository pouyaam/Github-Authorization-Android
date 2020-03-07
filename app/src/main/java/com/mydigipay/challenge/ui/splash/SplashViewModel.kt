package com.mydigipay.challenge.ui.splash

import androidx.lifecycle.viewModelScope
import com.mydigipay.challenge.base.BaseViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class SplashViewModel(model: SplashModel) : BaseViewModel(model) {

    init {
        viewModelScope.launch {
            delay(3000)
            if (model.isUserLogin()) {
                navigateTo(SplashFragmentDirections.actionSplashToProfile())
            } else {
                navigateTo(SplashFragmentDirections.actionSplashToHome())
            }
        }
    }

}