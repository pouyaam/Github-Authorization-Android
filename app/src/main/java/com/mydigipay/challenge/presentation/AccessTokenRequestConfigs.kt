package com.mydigipay.challenge.presentation

import java.io.Serializable

data class AccessTokenRequestConfigs(
    val clientId: String,
    val clientSecret: String,
    val code: String,
    val redirectUri: String,
    val state: String
) : Serializable