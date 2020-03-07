package com.mydigipay.challenge.ui.authorize.di

import com.mydigipay.challenge.ui.authorize.model.AuthorizeModel
import com.mydigipay.challenge.ui.authorize.viewmodel.AuthorizeViewModel
import org.koin.dsl.module

var authorizeModule = module {

    factory { AuthorizeModel() }

    factory { AuthorizeViewModel(get()) }
}