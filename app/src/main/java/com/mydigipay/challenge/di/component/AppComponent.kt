package com.mydigipay.challenge.di.component

import android.content.Context
import com.einhesari.batmanmovies.di.module.ViewModelModule
import com.mydigipay.challenge.di.module.DataSourceModule
import com.mydigipay.challenge.di.module.RepositoryModule
import com.mydigipay.challenge.di.module.SharedPrefsModule
import com.mydigipay.challenge.presentation.MainActivity
import dagger.BindsInstance
import dagger.Component
import dagger.Subcomponent

@Component(
    modules = [SharedPrefsModule::class,
        DataSourceModule::class,
        RepositoryModule::class
    ]
)
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

    @Subcomponent.Factory
    interface Factory {
        fun create(): ViewModelComponent
    }

}