package com.mydigipay.challenge.authorization

import com.mydigipay.challenge.model.Resource
import javax.inject.Inject

class GetAccessTokenUseCase @Inject constructor(private val repository: AccessTokenRepository) {

    suspend operator fun invoke(
        clientId: String,
        clientSecret: String,
        code: String,
        redirectUri: String,
        state: String = "0"
    ): Resource<AccessToken> =
        repository.getAccessToken(clientId, clientSecret, code, redirectUri, state)
}
