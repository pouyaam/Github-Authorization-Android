package com.mydigipay.challenge.di.component

import android.content.Context
import com.mydigipay.challenge.di.module.*
import com.mydigipay.challenge.presentation.auth.AuthActivity
import com.mydigipay.challenge.presentation.github.commit.CommitFragment
import com.mydigipay.challenge.presentation.github.search.SearchFragment
import dagger.BindsInstance
import dagger.Component
import dagger.Subcomponent
import javax.inject.Singleton

@Component(
    modules = [SharedPrefsModule::class,
        DataSourceModule::class,
        RepositoryModule::class,
        ApiModule::class
    ]
)
@Singleton
interface AppComponent {

    @Component.Factory
    interface Factory {
        fun create(@BindsInstance context: Context): AppComponent
    }

    val viewModelProviderFactory: ViewModelComponent.Factory

}

@Subcomponent(modules = [ViewModelModule::class])
interface ViewModelComponent {

    fun inject(authActivity: AuthActivity)
    fun inject(searchFragment: SearchFragment)
    fun inject(commitFragment: CommitFragment)

    @Subcomponent.Factory
    interface Factory {
        fun create(): ViewModelComponent
    }

}