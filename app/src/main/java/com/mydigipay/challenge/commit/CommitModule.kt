package com.mydigipay.challenge.commit

import com.mydigipay.challenge.commit.repository.CommitRepository
import com.mydigipay.challenge.commit.repository.CommitRepositoryImp
import com.mydigipay.challenge.commit.repository.remote.CommitService
import com.mydigipay.challenge.core.network.di.RETROFIT
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.core.qualifier.named
import org.koin.dsl.module
import retrofit2.Retrofit

val commitModule = module {
    factory { get<Retrofit>(named(RETROFIT)).create(CommitService::class.java) }
    factory { CommitRepositoryImp(get()) as CommitRepository }

    viewModel { CommitViewModel(get()) }
}