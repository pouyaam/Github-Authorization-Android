package com.mydigipay.challenge.data

import com.mydigipay.challenge.data.api.ResponseAccessToken
import com.mydigipay.challenge.domain.entities.AccessTokenEntity

fun ResponseAccessToken.toAccessTokenEntity() =
    AccessTokenEntity(
        accessToken,
        tokenType
    )