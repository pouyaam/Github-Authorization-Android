package com.mydigipay.challenge.authorization

import com.mydigipay.challenge.model.Resource
import javax.inject.Inject

class SaveAccessTokenUseCase @Inject constructor(private val repository: AccessTokenRepository) {

    suspend operator fun invoke(token: String): Resource<Unit> =
        repository.saveAccessToken(token)
}
