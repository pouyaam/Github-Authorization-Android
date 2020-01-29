package com.mydigipay.challenge.presentation

import com.mydigipay.challenge.domain.entities.AccessTokenEntity

fun AccessTokenEntity.toAccessTokenModel() = AccessTokenModel(accessToken, tokenType)