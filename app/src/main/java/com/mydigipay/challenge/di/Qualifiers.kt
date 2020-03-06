package com.mydigipay.challenge.di

import org.koin.core.qualifier.named

object Qualifiers {

    val LOGGING_INTERCEPTOR = named("LOGGING_INTERCEPTOR")
    val TOKEN_INTERCEPTOR = named("TOKEN_INTERCEPTOR")

    val READ_TIMEOUT = named("READ_TIMEOUT")
    val WRITE_TIMEOUT = named("WRITE_TIMEOUT")
    val CONNECTION_TIMEOUT = named("CONNECTION_TIMEOUT")

}