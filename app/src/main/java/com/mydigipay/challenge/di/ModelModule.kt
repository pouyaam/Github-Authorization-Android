package com.mydigipay.challenge.di

import com.mydigipay.challenge.ui.home.HomeModel
import com.mydigipay.challenge.ui.main.MainModel
import com.mydigipay.challenge.ui.profile.ProfileModel
import com.mydigipay.challenge.ui.search.SearchModel
import org.koin.dsl.module

val modelModule = module {
    factory { MainModel() }
    factory { HomeModel(get()) }
    factory { ProfileModel() }
    factory { SearchModel() }
}