package com.mydigipay.challenge.data.persist

interface SharedPrefWrapper {

    suspend fun saveToken(token: String)
    fun readToken(): String
    suspend fun isUserLoggedIn(): Boolean

}