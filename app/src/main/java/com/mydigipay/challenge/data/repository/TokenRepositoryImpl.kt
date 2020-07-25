package com.mydigipay.challenge.data.repository

import com.mydigipay.challenge.app.Const.CLIENT_ID
import com.mydigipay.challenge.app.Const.CLIENT_SECRET
import com.mydigipay.challenge.app.Const.REDIRECT_URI
import com.mydigipay.challenge.app.Const.STATE
import com.mydigipay.challenge.data.datasource.local.LocalAccessTokenDataSource
import com.mydigipay.challenge.data.datasource.remote.RemoteAccessTokenDataSource
import com.mydigipay.challenge.domain.repository.TokenRepository
import io.reactivex.Completable
import javax.inject.Inject


class TokenRepositoryImpl @Inject constructor(
    private val localAccessTokenDataSource: LocalAccessTokenDataSource,
    private val remoteAccessTokenDataSource: RemoteAccessTokenDataSource
) :
    TokenRepository {

    override fun saveToken(token: String): Completable {
        return localAccessTokenDataSource.saveToken(token)
    }

    override fun readToken(): String {
        return localAccessTokenDataSource.readToken()
    }

    override fun fetchAccessToken(code: String): Completable {
        return remoteAccessTokenDataSource.getAccessToken(
            CLIENT_ID,
            CLIENT_SECRET,
            code,
            REDIRECT_URI,
            STATE
        ).flatMapCompletable {
            saveToken(it.accessToken)
        }.onErrorResumeNext {
            Completable.error(it)
        }
    }

}