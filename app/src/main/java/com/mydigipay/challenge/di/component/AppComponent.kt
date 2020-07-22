package com.mydigipay.challenge.di.component

import android.content.Context
import com.einhesari.batmanmovies.di.module.ViewModelModule
import com.mydigipay.challenge.di.module.ApiModule
import com.mydigipay.challenge.di.module.DataSourceModule
import com.mydigipay.challenge.di.module.RepositoryModule
import com.mydigipay.challenge.di.module.SharedPrefsModule
import com.mydigipay.challenge.presentation.MainActivity
import com.mydigipay.challenge.presentation.github.GithubActivity
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

    fun inject(mainActivity: MainActivity)
    fun inject(githubActivity: GithubActivity)

    @Subcomponent.Factory
    interface Factory {
        fun create(): ViewModelComponent
    }

}