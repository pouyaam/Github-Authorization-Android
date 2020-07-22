package com.mydigipay.challenge.data.datasource.auth

import kotlinx.coroutines.Deferred

interface TokenRepository {
    fun saveToken(token: String) : Deferred<Unit>
    fun readToken(): Deferred<String>
}