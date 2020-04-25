package com.mydigipay.challenge.util

interface Setting {

    fun isDefaultUserSelected(): Boolean

    fun getSelectedUserLogin(): String?

    fun setSelectedUserLogin(login: String)

    fun clearSelectedUserLogin()
}