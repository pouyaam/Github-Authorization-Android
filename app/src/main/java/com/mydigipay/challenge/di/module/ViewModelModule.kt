package com.einhesari.batmanmovies.di.module

import androidx.lifecycle.ViewModel
import com.mydigipay.challenge.di.scope.ViewModelKey
import com.mydigipay.challenge.presentation.MainActivityViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class ViewModelModule {
    @Binds
    @IntoMap
    @ViewModelKey(MainActivityViewModel::class)
    abstract fun bindMainActivityViewModel(mainActivityViewModel: MainActivityViewModel): ViewModel

}