package com.einhesari.batmanmovies.di.module

import androidx.lifecycle.ViewModelProvider
import com.mydigipay.challenge.app.ViewModelProviderFactory
import dagger.Module

@Module
abstract class ViewModelFactoryModule {
     abstract fun bindViewModelFactory(viewModelProviderFactory: ViewModelProviderFactory): ViewModelProvider.Factory
}