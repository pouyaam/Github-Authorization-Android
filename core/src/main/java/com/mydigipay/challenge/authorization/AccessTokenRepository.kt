package com.mydigipay.challenge.authorization

import com.mydigipay.challenge.model.Resource
import javax.inject.Inject
import javax.inject.Named

class AccessTokenRepository @Inject constructor(
    @Named("local") private val localDataSource: AccessTokenDataSource,
    @Named("remote") private val remoteDataSource: AccessTokenDataSource
) {

    suspend fun getAccessToken(
        clientId: String,
        clientSecret: String,
        code: String,
        redirectUri: String,
        state: String = "0"
    ): Resource<AccessToken> =
        remoteDataSource.getAccessToken(clientId, clientSecret, code, redirectUri, state)

     suspend fun saveAccessToken(token: String): Resource<Unit> =
         localDataSource.saveAccessToken(token)

     suspend fun readAccessToken(): Resource<String> =
         localDataSource.readAccessToken()
}
