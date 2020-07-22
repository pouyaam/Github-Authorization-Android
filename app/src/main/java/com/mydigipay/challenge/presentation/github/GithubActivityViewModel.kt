package com.mydigipay.challenge.presentation.github

import androidx.lifecycle.ViewModel
import com.mydigipay.challenge.domain.usecase.AccessTokenUseCase
import javax.inject.Inject

class GithubActivityViewModel @Inject constructor(useCase: AccessTokenUseCase) : ViewModel() {

    fun fetchAccessToken(code: String) {

    }
}