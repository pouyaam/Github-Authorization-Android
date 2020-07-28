package com.mydigipay.challenge.framework.preference

import kotlinx.coroutines.Deferred

interface TokenDao {

    fun saveAccessTokenAsync(token: String): Deferred<Unit>

    fun readAccessTokenAsync(): Deferred<String>
}
