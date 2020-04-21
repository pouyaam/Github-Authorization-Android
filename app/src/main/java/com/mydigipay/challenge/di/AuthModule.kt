package com.mydigipay.challenge.di

import android.accounts.AccountManager
import com.mydigipay.challenge.auth.AuthenticationUtil
import com.mydigipay.challenge.auth.AuthenticationUtilImp
import org.koin.core.qualifier.named
import org.koin.dsl.module

const val TOKEN = "ACCESS_TOKEN"
const val CURRENT_USER = "CURRENT_USER"

val authModule = module {

    single { AccountManager.get(get()) }

    single<AuthenticationUtil> {
        AuthenticationUtilImp(get(), get())
    }

    factory() {
        val authUtil: AuthenticationUtil = get()
        authUtil.authenticationState()
    }

    factory(named(TOKEN)) {
        val authUtil: AuthenticationUtil = get()
        authUtil.getAccessToken()
    }

    factory(named(CURRENT_USER)) {
        val authUtil: AuthenticationUtil = get()
        authUtil.getCurrentUser()
    }
}