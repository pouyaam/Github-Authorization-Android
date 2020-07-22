package com.mydigipay.challenge.data.datasource.auth

import io.reactivex.Completable
import io.reactivex.Single

interface LocalAccessTokenDataSource {
    fun readToken(): String
}