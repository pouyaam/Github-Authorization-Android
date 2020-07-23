package com.einhesari.batmanmovies.di.module

import androidx.lifecycle.ViewModel
import com.mydigipay.challenge.di.scope.ViewModelKey
import com.mydigipay.challenge.presentation.AuthActivityViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class ViewModelModule {
    @Binds
    @IntoMap
    @ViewModelKey(AuthActivityViewModel::class)
    abstract fun bindAuthActivityViewModel(authActivityViewModel: AuthActivityViewModel): ViewModel

}