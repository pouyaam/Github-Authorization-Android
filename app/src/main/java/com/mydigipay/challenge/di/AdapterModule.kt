package com.mydigipay.challenge.di

import com.mydigipay.challenge.ui.repository.list.GitRepoAdapter
import org.koin.dsl.module

val adapterModule = module {

    factory { GitRepoAdapter() }

}