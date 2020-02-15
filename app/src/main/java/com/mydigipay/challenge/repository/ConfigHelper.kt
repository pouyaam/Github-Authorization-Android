package  com.mydigipay.challenge.repository

import android.annotation.SuppressLint
import android.content.Context
import android.content.SharedPreferences


const val AUTH_CODE = "auth2"
lateinit var sharedPreferences: SharedPreferences


private val authorization: String?
    get() = getStringPreference(AUTH_CODE)

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

fun saveAuthorization(authorization: String) {
    putStringPreference(
        AUTH_CODE,
        authorization
    )
}

fun getShownIqTests() = sharedPreferences.getStringSet("shownIqTests", mutableSetOf())
fun isShownIqTests(id: Long) =
    sharedPreferences.getStringSet("shownIqTests", mutableSetOf())?.contains(id.toString()) ?: false

fun putIqTestAsShown(id: Long) = getShownIqTests()?.apply {
    add(id.toString())
    sharedPreferences.edit().putStringSet("shownIqTests", this).apply()
}

fun removeIqTestAsShown(id: Long) = getShownIqTests()?.apply {
    remove(id.toString())
    sharedPreferences.edit().putStringSet("shownIqTests", this).apply()
}

fun getShownMBTITests() = sharedPreferences.getStringSet("shownMBTITests", mutableSetOf())
fun isShownMBTITests(id: Long) =
    sharedPreferences.getStringSet("shownMBTITests", mutableSetOf())?.contains(id.toString())
        ?: false

fun putMBTITestAsShown(id: Long) = getShownMBTITests()?.apply {
    add(id.toString())
    sharedPreferences.edit().putStringSet("shownMBTITests", this).apply()
}

fun removeMBTITestAsShown(id: Long) = getShownMBTITests()?.apply {
    remove(id.toString())
    sharedPreferences.edit().putStringSet("shownMBTITests", this).apply()
}

@Synchronized
fun getToken(): String? {
    return if (authorization == null) null else getStringPreference(
        AUTH_CODE
    ).toString()
}

@Synchronized
fun removeAuthorization() = sharedPreferences.edit().remove(AUTH_CODE).apply()


fun isUserSignedIn() = getToken().isNullOrBlank().not()

