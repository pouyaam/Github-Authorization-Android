package com.mydigipay.challenge.di

import com.mydigipay.challenge.data.network.api.AccessTokenApi
import com.mydigipay.challenge.data.network.api.CommitsApi
import com.mydigipay.challenge.data.network.api.GithubRepositoryApi
import com.mydigipay.challenge.data.network.api.UserApi
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.create

val restModule = module {

    factory<AccessTokenApi> { get<Retrofit>().create() }
    factory<GithubRepositoryApi> { get<Retrofit>().create() }
    factory<UserApi> { get<Retrofit>().create() }
    factory<CommitsApi> { get<Retrofit>().create() }
}