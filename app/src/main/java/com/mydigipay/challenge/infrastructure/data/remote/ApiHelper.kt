package com.mydigipay.challenge.infrastructure.data.remote

import com.mydigipay.challenge.infrastructure.data.model.api.RequestAccessToken
import com.mydigipay.challenge.infrastructure.data.model.api.ResponseAccessToken
import com.mydigipay.challenge.infrastructure.network.RetrofitInterface
import com.mydigipay.challenge.infrastructure.network.RetrofitUtils
import io.reactivex.Single
import javax.inject.Singleton

interface ApiHelper {
    fun accessToken(requestAccessToken: RequestAccessToken) : Single<ResponseAccessToken>
}

@Singleton
class ApiHelperImp : ApiHelper {

    override fun accessToken(requestAccessToken: RequestAccessToken): Single<ResponseAccessToken> =
        RetrofitUtils()
            .getRetrofit()
            .create(RetrofitInterface::class.java)
            .accessToken(requestAccessToken)

}