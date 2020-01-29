package com.mydigipay.challenge.domain.repositories.token

import com.mydigipay.challenge.domain.repositories.token.local.LocalAccessTokenDataSource
import com.mydigipay.challenge.domain.repositories.token.remote.RemoteAccessTokenDataSource

class TokenRepositoryImpl(
    private val remoteAccessTokenDataSource: RemoteAccessTokenDataSource,
    private val localAccessTokenDataSource: LocalAccessTokenDataSource
) : TokenRepository