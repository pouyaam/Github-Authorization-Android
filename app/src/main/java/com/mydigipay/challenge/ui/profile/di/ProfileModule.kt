package com.mydigipay.challenge.ui.profile.di

import com.mydigipay.challenge.di.Qualifiers
import com.mydigipay.challenge.ui.profile.model.ProfileModel
import com.mydigipay.challenge.ui.profile.viewmodel.ProfileViewModel
import org.koin.dsl.module

var profileModule = module {

    factory {
        ProfileModel(
            get(Qualifiers.getNamedDependencyRepository(Qualifiers.tokenDataSource)),
            get(Qualifiers.getNamedDependencyRepository(Qualifiers.userDataSource))
        )
    }

    factory { ProfileViewModel(get(), get()) }

}