package com.mydigipay.challenge.presentation

import androidx.lifecycle.ViewModel
import com.mydigipay.challenge.domain.usecase.AuthCheckingUseCase
import javax.inject.Inject

class MainActivityViewModel @Inject constructor(private val useCase: AuthCheckingUseCase) :
    ViewModel() {

    fun isUserAuthorized(): Boolean {
        return useCase.isUserAuthorized()
    }
}