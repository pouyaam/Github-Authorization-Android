package com.mydigipay.challenge.data.repository

import com.mydigipay.challenge.data.datasource.auth.LocalAccessTokenDataSource
import com.mydigipay.challenge.data.datasource.auth.RemoteAccessTokenDataSource
import com.mydigipay.challenge.domain.repository.TokenRepository
import io.reactivex.Completable
import javax.inject.Inject


class TokenRepositoryImpl @Inject constructor(
    private val localAccessTokenDataSource: LocalAccessTokenDataSource
) :
    TokenRepository {

    override fun saveToken(token: String): Completable {
        TODO("Not yet implemented")
    }

    override fun readToken(): String {
        return localAccessTokenDataSource.readToken()
    }

}