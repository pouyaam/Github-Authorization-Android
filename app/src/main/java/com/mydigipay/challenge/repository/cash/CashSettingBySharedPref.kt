package com.mydigipay.challenge.util

import android.content.SharedPreferences
import androidx.core.content.edit
import com.mydigipay.challenge.auth.AccountGeneral.CURRENT_USER_LOGIN_KEY
import com.mydigipay.challenge.auth.AccountGeneral.IS_USER_SELECTED_KEY

class CashSettingBySharedPref(
    private val sharedPreferences: SharedPreferences
) : CashSetting {

    override fun isDefaultUserSelected() = sharedPreferences.getBoolean(
        IS_USER_SELECTED_KEY,
        false
    )

    override fun getSelectedUserLogin() = sharedPreferences.getString(
        CURRENT_USER_LOGIN_KEY,
        null
    )

    override fun setSelectedUserLogin(login: String) {
        sharedPreferences.edit {
            putString(CURRENT_USER_LOGIN_KEY, login)
            putBoolean(IS_USER_SELECTED_KEY, true)
        }
    }

    override fun clearSelectedUserLogin() {
        sharedPreferences.edit {
            putString(CURRENT_USER_LOGIN_KEY, null)
            putBoolean(IS_USER_SELECTED_KEY, false)
        }
    }
}