package com.mydigipay.challenge.data.datasource.local

import io.reactivex.Completable

interface LocalAccessTokenDataSource {
    fun readToken(): String
    fun saveToken(token: String): Completable
}