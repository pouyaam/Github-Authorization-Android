package com.mydigipay.challenge.datasource.remote

import com.mydigipay.challenge.data.datasource.remote.RemoteAccessTokenDataSource
import com.mydigipay.challenge.data.datasource.api.ApiService
import com.mydigipay.challenge.data.model.RequestAccessToken
import com.mydigipay.challenge.data.model.ResponseAccessToken
import io.reactivex.Single

class RemoteAccessTokenDataSourceImpl(private val apiService: ApiService) :
    RemoteAccessTokenDataSource {
    override fun getAccessToken(
        clientId: String,
        clientSecret: String,
        code: String,
        redirectUrl: String,
        state: String
    ): Single<ResponseAccessToken> {
        return apiService.getAccessToken(
            RequestAccessToken(
                clientId,
                clientSecret,
                code,
                redirectUrl,
                state
            )
        )
    }
}