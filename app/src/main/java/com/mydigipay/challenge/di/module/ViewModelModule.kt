package com.einhesari.batmanmovies.di.module

import androidx.lifecycle.ViewModel
import com.mydigipay.challenge.di.scope.ViewModelKey
import com.mydigipay.challenge.presentation.auth.AuthActivityViewModel
import com.mydigipay.challenge.presentation.github.commit.CommitFragmentViewModel
import com.mydigipay.challenge.presentation.github.search.SearchFragmentViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class ViewModelModule {
    @Binds
    @IntoMap
    @ViewModelKey(AuthActivityViewModel::class)
    abstract fun bindAuthActivityViewModel(authActivityViewModel: AuthActivityViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(SearchFragmentViewModel::class)
    abstract fun bindSearchFragmentViewModel(searchFragmentViewModel: SearchFragmentViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(CommitFragmentViewModel::class)
    abstract fun bindCommitFragmentViewModel(commitFragmentViewMode : CommitFragmentViewModel): ViewModel

}