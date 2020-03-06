package com.mydigipay.challenge.di

import com.mydigipay.challenge.data.network.oauth.AccessTokenService
import org.koin.dsl.module
import retrofit2.Retrofit

val restModule = module {

    factory { get<Retrofit>().create(AccessTokenService::class.java) }
}