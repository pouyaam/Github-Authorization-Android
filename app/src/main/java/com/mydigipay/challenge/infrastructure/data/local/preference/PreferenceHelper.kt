package com.mydigipay.challenge.infrastructure.data.local.preference

import android.content.Context
import android.content.SharedPreferences
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Created by Mahdi Zare Tahghigh Doost on 5/10/2020.
 *
 * mahdiZTD@gmail.com
 */

interface PreferenceHelper {

    fun getToken(): String?

    fun setToken(accessToken: String?)

}

@Singleton
class PreferenceHelperImp @Inject constructor(context: Context) : PreferenceHelper {

    companion object {
        const val PREF_KEY_ACCESS_TOKEN = "PREF_KEY_ACCESS_TOKEN"
    }

    private val mPrefs: SharedPreferences =
        context.getSharedPreferences("GUNG_HO_PREF", Context.MODE_PRIVATE)


    override fun getToken(): String? = mPrefs.getString(
        PREF_KEY_ACCESS_TOKEN,
        null
    )

    override fun setToken(accessToken: String?) {
        mPrefs.edit().putString(PREF_KEY_ACCESS_TOKEN, accessToken).apply()
    }

}