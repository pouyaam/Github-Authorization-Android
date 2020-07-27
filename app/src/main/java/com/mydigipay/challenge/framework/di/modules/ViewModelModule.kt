package com.mydigipay.challenge.framework.di.modules

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.mydigipay.challenge.common.help.ViewModelFactory
import com.mydigipay.challenge.framework.di.keys.ViewModelKey
import com.mydigipay.challenge.presentation.viewmodel.AccessTokenViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class ViewModelModule {

    @Binds
    abstract fun bindViewModelFactory(viewModelFactory: ViewModelFactory): ViewModelProvider.Factory

    @IntoMap
    @Binds
    @ViewModelKey(AccessTokenViewModel::class)
    abstract fun provideAccessTokenViewModel(viewModel: AccessTokenViewModel): ViewModel
}
