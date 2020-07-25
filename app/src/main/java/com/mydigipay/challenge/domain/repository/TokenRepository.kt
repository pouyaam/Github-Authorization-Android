package com.mydigipay.challenge.domain.repository

import io.reactivex.Completable

interface TokenRepository {
    fun saveToken(token: String): Completable
    fun readToken(): String
    fun fetchAccessToken(code: String): Completable
}