package com.mydigipay.challenge.di.module

import com.mydigipay.challenge.data.datasource.local.LocalAccessTokenDataSource
import com.mydigipay.challenge.data.datasource.remote.RemoteAccessTokenDataSource
import com.mydigipay.challenge.data.datasource.remote.GithubDataSource
import com.mydigipay.challenge.data.repository.GithubRepositoryImpl
import com.mydigipay.challenge.data.repository.TokenRepositoryImpl
import com.mydigipay.challenge.domain.repository.GithubRepository
import com.mydigipay.challenge.domain.repository.TokenRepository
import dagger.Module
import dagger.Provides

@Module
class RepositoryModule {

    @Provides
    fun provideTokenRepository(
        localAccessTokenDataSource: LocalAccessTokenDataSource,
        remoteAccessTokenDataSource: RemoteAccessTokenDataSource
    ): TokenRepository {
        return TokenRepositoryImpl(localAccessTokenDataSource, remoteAccessTokenDataSource)
    }

    @Provides
    fun provideGithubRepository(githubDataSource: GithubDataSource): GithubRepository {
        return GithubRepositoryImpl(githubDataSource)
    }
}