package com.mydigipay.challenge.ui.detail.di

import com.mydigipay.challenge.core.BaseFragment
import com.mydigipay.challenge.di.Qualifiers.commitDataSource
import com.mydigipay.challenge.di.Qualifiers.getNamedDependencyRepository
import com.mydigipay.challenge.ui.detail.model.DetailModel
import com.mydigipay.challenge.ui.detail.viewmodel.DetailViewModel
import org.koin.dsl.module

var detailModule = module {

    factory { DetailModel(get(getNamedDependencyRepository(commitDataSource))) }

    factory { (fragment: BaseFragment) -> DetailViewModel(fragment, get(), get()) }
}