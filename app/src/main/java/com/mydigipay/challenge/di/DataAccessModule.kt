package com.mydigipay.challenge.di

import com.mydigipay.challenge.dataaccess.TokenDataSource
import com.mydigipay.challenge.dataaccess.cache.TokenCacheDataSource
import com.mydigipay.challenge.dataaccess.local.TokenLocalDataSource
import com.mydigipay.challenge.dataaccess.remote.TokenRemoteDataSource
import com.mydigipay.challenge.dataaccess.repository.TokenRepository
import com.mydigipay.challenge.di.Qualifiers.NAMED_DEPENDENCY_CACHE
import com.mydigipay.challenge.di.Qualifiers.NAMED_DEPENDENCY_LOCAL
import com.mydigipay.challenge.di.Qualifiers.NAMED_DEPENDENCY_REMOTE
import com.mydigipay.challenge.di.Qualifiers.NAMED_DEPENDENCY_REPOSITORY
import org.koin.dsl.module

val dataAccessModule = module {

    single<TokenDataSource>(NAMED_DEPENDENCY_LOCAL) {
        TokenLocalDataSource()
    }

    single<TokenDataSource>(NAMED_DEPENDENCY_CACHE) {
        TokenCacheDataSource(get())
    }

    single<TokenDataSource>(NAMED_DEPENDENCY_REMOTE) {
        TokenRemoteDataSource(get())
    }

    single<TokenDataSource>(NAMED_DEPENDENCY_REPOSITORY) {
        TokenRepository(
            get(NAMED_DEPENDENCY_LOCAL),
            get(NAMED_DEPENDENCY_CACHE),
            get(NAMED_DEPENDENCY_REMOTE)
        )
    }
}