package com.mydigipay.challenge.data.datasource.auth

import io.reactivex.Completable

interface LocalAccessTokenDataSource {
    fun readToken(): String
    fun saveToken(token: String): Completable
}