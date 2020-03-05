package com.mydigipay.challenge.di

import com.mydigipay.challenge.data.network.api.gitrepo.GitRepoService
import com.mydigipay.challenge.data.network.api.oauth.AccessTokenService
import org.koin.dsl.module
import retrofit2.Retrofit

val restModule = module {

    factory { get<Retrofit>().create(AccessTokenService::class.java) }
    factory { get<Retrofit>().create(GitRepoService::class.java) }

}