package  com.mydigipay.challenge.utils

import android.annotation.SuppressLint
import android.content.Context
import android.content.SharedPreferences

const val CODE = "CODE"
const val AUTH_CODE = "auth2"
lateinit var sharedPreferences: SharedPreferences


 var token: String?
    get() = getStringPreference(AUTH_CODE)
    set(value) {
        value ?: run {
            removeStringPreference(AUTH_CODE)
            return
        }
        putStringPreference(
            AUTH_CODE,
            value
        )
    }

 var githubCode: String?
    get() = getStringPreference(CODE)
    set(value) {
        value ?: run {
            removeStringPreference(CODE)
            return
        }
        putStringPreference(
            CODE,
            value
        )
    }


fun initPreferenceUtils(context: Context) {
    sharedPreferences = context.getSharedPreferences(context.packageName, Context.MODE_PRIVATE)
}

fun getStringPreference(tag: String): String? {
    return sharedPreferences.getString(tag, null)
}

@SuppressLint("ApplySharedPref")
fun putStringPreference(tag: String, value: String) {
    sharedPreferences.edit().putString(tag, value).commit()
}

@SuppressLint("ApplySharedPref")
fun removeStringPreference(tag: String) {
    sharedPreferences.edit().remove(tag).commit()
}

fun putLongPreferenceTagStartWith(tag: String, value: Long) {
    val editor = sharedPreferences.edit()
    for (key in sharedPreferences.all.keys)
        if (key.startsWith(tag))
            editor.putLong(key, value)
    editor.apply()
}

fun getLongPreference(tag: String): Long {
    return sharedPreferences.getLong(tag, -1L)
}

fun getIntPreference(tag: String): Int {
    return sharedPreferences.getInt(tag, -1)
}

fun putIntPreference(tag: String, value: Int) {
    sharedPreferences.edit().putInt(tag, value).apply()
}

fun getBooleanPreference(pref: String): Boolean {
    return sharedPreferences.getBoolean(pref, false)
}

fun putBooleanPreference(pref: String, value: Boolean) {
    sharedPreferences.edit().putBoolean(pref, value).apply()
}


fun isUserSignedIn() = token.isNullOrBlank().not()

