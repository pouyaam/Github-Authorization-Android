package com.mydigipay.challenge.domain.repositories.oauth

import com.mydigipay.challenge.data.RequestAccessToken
import com.mydigipay.challenge.data.ResponseAccessToken

interface AccessTokenDataSource {
    suspend fun accessToken(requestAccessToken: RequestAccessToken): ResponseAccessToken
}