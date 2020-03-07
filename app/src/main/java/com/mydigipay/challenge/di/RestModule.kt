package com.mydigipay.challenge.di

import com.mydigipay.challenge.data.network.api.AccessTokenApi
import com.mydigipay.challenge.data.network.api.GithubRepositoryApi
import com.mydigipay.challenge.data.network.api.UserApi
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.create

val restModule = module {

    factory { get<Retrofit>().create(AccessTokenApi::class.java) }
    factory<GithubRepositoryApi> { get<Retrofit>().create() }
    factory<UserApi> { get<Retrofit>().create() }
}