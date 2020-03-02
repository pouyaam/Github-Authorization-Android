package com.mydigipay.challenge.di

import com.mydigipay.challenge.network.oauth.AccessTokenService
import org.koin.core.qualifier.named
import org.koin.dsl.module
import retrofit2.Retrofit

val restModule = module {

    factory { get<Retrofit>(named(RETROFIT)).create(AccessTokenService::class.java) }

}