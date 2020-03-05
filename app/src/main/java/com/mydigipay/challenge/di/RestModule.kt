package com.mydigipay.challenge.di

import com.mydigipay.challenge.data.network.api.commit.CommitService
import com.mydigipay.challenge.data.network.api.gitrepo.GitRepoService
import com.mydigipay.challenge.data.network.api.oauth.AccessTokenService
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.create

val restModule = module {

    factory<AccessTokenService> { get<Retrofit>().create() }
    factory<GitRepoService> { get<Retrofit>().create() }
    factory<CommitService> { get<Retrofit>().create() }

}