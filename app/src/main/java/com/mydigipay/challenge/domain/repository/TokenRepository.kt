package com.mydigipay.challenge.domain.repository

import io.reactivex.Completable
import io.reactivex.Single
import kotlinx.coroutines.Deferred

interface TokenRepository {
    fun saveToken(token: String): Completable
    fun readToken(): String
}