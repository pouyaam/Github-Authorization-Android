package com.mydigipay.challenge.data.datasource.remote

import com.mydigipay.challenge.data.model.ResponseAccessToken
import io.reactivex.Single

interface RemoteAccessTokenDataSource {
    fun getAccessToken(
        clientId: String,
        clientSecret: String,
        code: String,
        redirectUrl: String,
        state: String
    ): Single<ResponseAccessToken>
}