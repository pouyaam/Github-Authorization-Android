package com.mydigipay.challenge.util

interface CashSetting {

    fun isDefaultUserSelected(): Boolean

    fun getSelectedUserLogin(): String?

    fun setSelectedUserLogin(login: String?)

    fun clearSelectedUserLogin()
}