package com.mydigipay.challenge.framework.preference

import com.mydigipay.challenge.authorization.AccessTokenDataSource
import com.mydigipay.challenge.model.Resource
import javax.inject.Inject

class LocalAccessTokenDataSource @Inject constructor(
    private val service: TokenDao
) : AccessTokenDataSource {

    override suspend fun saveAccessToken(token: String): Resource<Unit> {
        return Resource.success(service.saveAccessTokenAsync(token).await())
    }

    override suspend fun readAccessToken(): Resource<String> {
        return Resource.success(service.readAccessTokenAsync().await())
    }
}
