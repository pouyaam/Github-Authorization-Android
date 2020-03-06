package com.mydigipay.challenge.profile

import com.mydigipay.challenge.core.network.di.RETROFIT
import com.mydigipay.challenge.profile.repository.ProfileRepository
import com.mydigipay.challenge.profile.repository.ProfileRepositoryImp
import com.mydigipay.challenge.profile.repository.remote.ProfileService
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.core.qualifier.named
import org.koin.dsl.module
import retrofit2.Retrofit

val profileModule = module {
    factory { get<Retrofit>(named(RETROFIT)).create(ProfileService::class.java) }
    factory { ProfileRepositoryImp(get()) as ProfileRepository }

    viewModel { ProfileViewModel(get()) }
}