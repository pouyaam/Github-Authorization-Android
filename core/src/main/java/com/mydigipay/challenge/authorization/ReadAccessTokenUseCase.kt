package com.mydigipay.challenge.authorization

import com.mydigipay.challenge.model.Resource
import javax.inject.Inject

class ReadAccessTokenUseCase @Inject constructor(private val repository: AccessTokenRepository) {

    suspend operator fun invoke(): Resource<String> =
        repository.readAccessToken()
}
