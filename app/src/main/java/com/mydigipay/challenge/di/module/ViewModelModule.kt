package com.mydigipay.challenge.di.module

import androidx.lifecycle.ViewModel
import com.mydigipay.challenge.di.scope.ViewModelKey
import com.mydigipay.challenge.presentation.auth.AuthViewModel
import com.mydigipay.challenge.presentation.github.commit.CommitViewModel
import com.mydigipay.challenge.presentation.github.search.SearchViewModel
import com.mydigipay.challenge.presentation.github.user.UserProfileViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class ViewModelModule {
    @Binds
    @IntoMap
    @ViewModelKey(AuthViewModel::class)
    abstract fun bindAuthViewModel(authViewModel: AuthViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(SearchViewModel::class)
    abstract fun bindSearchViewModel(searchViewModel: SearchViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(CommitViewModel::class)
    abstract fun bindCommitViewModel(commitViewMode: CommitViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(UserProfileViewModel::class)
    abstract fun bindUserProfileViewModel(userProfileViewModel: UserProfileViewModel): ViewModel

}