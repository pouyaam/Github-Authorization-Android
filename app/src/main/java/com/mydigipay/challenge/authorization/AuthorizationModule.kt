package com.mydigipay.challenge.authorization

import com.mydigipay.challenge.authorization.repository.oauth.AccessTokenService
import com.mydigipay.challenge.authorization.repository.oauth.AccessTokenDataSource
import com.mydigipay.challenge.authorization.repository.oauth.AccessTokenDataSourceImpl
import com.mydigipay.challenge.authorization.repository.token.TokenRepository
import com.mydigipay.challenge.authorization.repository.token.TokenRepositoryImpl
import com.mydigipay.challenge.core.network.di.RETROFIT
import org.koin.core.qualifier.named
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit

val authorizationModule = module {
    single { TokenRepositoryImpl(get()) as TokenRepository }

    factory { get<Retrofit>(named(RETROFIT)).create(AccessTokenService::class.java) }
    factory { AccessTokenDataSourceImpl(get()) as AccessTokenDataSource }

    viewModel { AuthorizationViewModel(get(), get()) }
}