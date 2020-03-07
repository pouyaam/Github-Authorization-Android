package com.mydigipay.challenge.ui.authorize.di

import com.mydigipay.challenge.core.BaseFragment
import com.mydigipay.challenge.di.Qualifiers.getNamedDependencyRepository
import com.mydigipay.challenge.di.Qualifiers.tokenDataSource
import com.mydigipay.challenge.ui.authorize.model.AuthorizeModel
import com.mydigipay.challenge.ui.authorize.viewmodel.AuthorizeViewModel
import org.koin.dsl.module

var authorizeModule = module {

    factory { AuthorizeModel(get(getNamedDependencyRepository(tokenDataSource))) }

    factory { (fragment: BaseFragment) -> AuthorizeViewModel(fragment, get(), get()) }
}