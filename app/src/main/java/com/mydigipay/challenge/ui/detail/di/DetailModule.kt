package com.mydigipay.challenge.ui.detail.di

import com.mydigipay.challenge.ui.detail.model.DetailModel
import com.mydigipay.challenge.ui.detail.viewmodel.DetailViewModel
import org.koin.dsl.module

var detailModule = module {

    factory { DetailModel() }

    factory { DetailViewModel(get()) }
}