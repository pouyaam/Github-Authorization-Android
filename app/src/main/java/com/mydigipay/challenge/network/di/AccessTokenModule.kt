package com.mydigipay.challenge.network.di

import com.mydigipay.challenge.network.oauth.GithubApiService
import org.koin.core.qualifier.named
import org.koin.dsl.module
import retrofit2.Retrofit

val accessTokenModule = module {
    factory { get<Retrofit>(named(RETROFIT)).create(GithubApiService::class.java) }
}