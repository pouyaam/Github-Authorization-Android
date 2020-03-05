package com.mydigipay.challenge.ui.profile.di

import com.mydigipay.challenge.ui.profile.model.ProfileModel
import com.mydigipay.challenge.ui.profile.viewmodel.ProfileViewModel
import org.koin.dsl.module

var profileModule = module {

    factory { ProfileModel() }

    factory { ProfileViewModel(get()) }

}