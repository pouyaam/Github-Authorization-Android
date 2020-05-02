package com.mydigipay.challenge.di

import android.accounts.AccountManager
import com.mydigipay.challenge.auth.AuthenticationUtil
import com.mydigipay.challenge.auth.AuthenticationUtilImp
import org.koin.core.qualifier.named
import org.koin.dsl.module

const val ACCESS_TOKEN = "ACCESS_TOKEN"
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

    single(named(ACCESS_TOKEN)) {
        val authUtil: AuthenticationUtil = get()
        val token = authUtil.getAccessToken()
        token ?: ""
    }

    factory(named(CURRENT_USER)) {
        val authUtil: AuthenticationUtil = get()
        authUtil.getCurrentUser()
    }
}