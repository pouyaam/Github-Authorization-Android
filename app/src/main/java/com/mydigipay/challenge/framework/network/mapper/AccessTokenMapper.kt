package com.mydigipay.challenge.framework.network.mapper

import com.mydigipay.challenge.authorization.AccessToken
import com.mydigipay.challenge.framework.network.response.AccessTokenResponse
import com.mydigipay.challenge.mapper.DataMapper
import javax.inject.Inject

class AccessTokenMapper @Inject constructor() : DataMapper<AccessToken, AccessTokenResponse>() {

    override fun transformToEntity(model: AccessToken): AccessTokenResponse? {
        // Unnecessary transform
        return null
    }

    override fun transformToModel(entity: AccessTokenResponse): AccessToken? {
        return AccessToken(
            entity.accessToken,
            entity.tokenType
        )
    }
}
