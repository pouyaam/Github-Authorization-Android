package com.mydigipay.challenge.di.component

import android.content.Context
import dagger.BindsInstance
import dagger.Component

@Component(modules = [])
interface AppComponent {

    @Component.Factory
    interface Factory {
        fun create(@BindsInstance context: Context): AppComponent
    }
}