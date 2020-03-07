package com.mydigipay.challenge.ui.search.di

import com.mydigipay.challenge.core.BaseFragment
import com.mydigipay.challenge.di.Qualifiers.getNamedDependencyRepository
import com.mydigipay.challenge.di.Qualifiers.projectDataSource
import com.mydigipay.challenge.ui.search.model.SearchModel
import com.mydigipay.challenge.ui.search.viewmodel.SearchViewModel
import org.koin.dsl.module

var searchModule = module {

    factory { SearchModel(get(getNamedDependencyRepository(projectDataSource))) }

    factory { (fragment: BaseFragment) -> SearchViewModel(fragment, get(), get()) }

}