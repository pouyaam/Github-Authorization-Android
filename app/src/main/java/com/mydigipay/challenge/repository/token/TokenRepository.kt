package com.mydigipay.challenge.repository.token

import kotlinx.coroutines.Deferred

interface TokenRepository {
    fun saveToken(token: String) : Deferred<Unit>
    fun readToken(): Deferred<String>
}