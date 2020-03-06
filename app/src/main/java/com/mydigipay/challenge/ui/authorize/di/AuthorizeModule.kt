package com.mydigipay.challenge.ui.authorize.di

import com.mydigipay.challenge.core.BaseFragment
import com.mydigipay.challenge.di.Qualifiers.NAMED_DEPENDENCY_REPOSITORY
import com.mydigipay.challenge.ui.authorize.model.AuthorizeModel
import com.mydigipay.challenge.ui.authorize.viewmodel.AuthorizeViewModel
import org.koin.dsl.module

var authorizeModule = module {

    factory { AuthorizeModel(get(NAMED_DEPENDENCY_REPOSITORY)) }

    factory { (fragment: BaseFragment) -> AuthorizeViewModel(fragment, get(), get()) }
}