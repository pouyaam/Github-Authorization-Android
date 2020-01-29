package com.mydigipay.challenge.presentation.model

import com.mydigipay.challenge.domain.entities.AccessTokenEntity
import com.mydigipay.challenge.presentation.model.AccessTokenModel

fun AccessTokenEntity.toAccessTokenModel() =
    AccessTokenModel(
        accessToken,
        tokenType
    )