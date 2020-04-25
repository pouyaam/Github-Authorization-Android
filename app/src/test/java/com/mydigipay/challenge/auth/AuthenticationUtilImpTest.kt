package com.mydigipay.challenge.auth

import android.accounts.Account
import android.accounts.AccountManager
import android.app.Activity
import android.content.SharedPreferences
import android.os.Build
import androidx.preference.PreferenceManager
import androidx.test.core.app.ApplicationProvider
import com.google.common.truth.Truth.assertThat
import com.mydigipay.challenge.data.setting.SettingBySharedPref
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.koin.core.context.stopKoin
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config
import testUser1
import testUser1Token
import testUser2
import testUser2Token
import testUser3
import testUser3Token

@RunWith(RobolectricTestRunner::class)
@Config(sdk = [Build.VERSION_CODES.O_MR1])
class AuthenticationUtilImpTest {
    private lateinit var am: AccountManager
    private lateinit var sharedPreferences: SharedPreferences
    private lateinit var activity: Activity
    private lateinit var authenticationUtil: AuthenticationUtil


    @Before
    fun setUp() {
        am = AccountManager.get(ApplicationProvider.getApplicationContext())
        activity = Activity()
        sharedPreferences =
            PreferenceManager
                .getDefaultSharedPreferences(ApplicationProvider.getApplicationContext())

        // Remove All accounts
        am.getAccountsByType(AccountGeneral.ACCOUNT_TYPE).forEach {
            am.removeAccount(
                it,
                activity,
                {},
                null
            )
        }

        authenticationUtil = AuthenticationUtilImp(
            am,
            SettingBySharedPref(sharedPreferences)
        )
    }

    @Test
    fun noAccountExist_THEN_state_UNAUTHENTCATED() {
        assertThat(am.getAccountsByType(AccountGeneral.ACCOUNT_TYPE)).hasLength(0)

        assertThat(authenticationUtil.authenticationState())
            .isEqualTo(AuthenticationState.UNAUTHENTICATED)

    }

    @After
    fun tearDown() {
        stopKoin()
    }

    @Test
    fun nullDefaultUser_THEN_getUserReturnNull() {
        addTestAccounts()
        val editor = sharedPreferences.edit()
        editor.putString(AccountGeneral.CURRENT_USER_LOGIN_KEY, null)
        editor.commit()

        assertThat(authenticationUtil.getCurrentUser()).isNull()
    }

    @Test
    fun defaultUserIsSelected_THEN_ReturnUser() {
        addTestUser()
        val editor = sharedPreferences.edit()
        editor.putString(AccountGeneral.CURRENT_USER_LOGIN_KEY, testUser3.login)
        editor.commit()

        assertThat(authenticationUtil.getCurrentUser()).isNotNull()
        assertThat(authenticationUtil.getCurrentUser()).isEqualTo(testUser3)
    }

    @Test
    fun getAccessToken() {
        addTestUser()
        val editor = sharedPreferences.edit()
        editor.putString(AccountGeneral.CURRENT_USER_LOGIN_KEY, testUser2.login)
        editor.commit()

        assertThat(authenticationUtil.getAccessToken()).isNotNull()
        assertThat(authenticationUtil.getAccessToken()).isEqualTo(testUser2Token)
    }

    @Test
    fun getAllUsers() {
        addTestUser()

        val allUser = authenticationUtil.getAllUsers()

        assertThat(allUser).hasSize(3)
        assertThat(testUser1).isIn(allUser)
        assertThat(testUser2).isIn(allUser)
        assertThat(testUser3).isIn(allUser)
    }

    @Test
    fun addAccount() {
        addTestUser()
        val accounts = am.getAccountsByType(AccountGeneral.ACCOUNT_TYPE)

        assertThat(accounts).hasLength(3)
    }

    @Test
    fun removeAllAccounts() {
        addTestAccounts()

        authenticationUtil.removeAllAccounts()

        assertThat(am.getAccountsByType(AccountGeneral.ACCOUNT_TYPE)).hasLength(0)
    }

    @Test
    fun removeAccount() {
        addTestAccounts()
        authenticationUtil.removeAccount(testUser1.login)

        assertThat(am.getAccountsByType(AccountGeneral.ACCOUNT_TYPE)).hasLength(2)
    }

    private fun addTestUser() {
        authenticationUtil.addAccount(testUser1Token, testUser1)
        authenticationUtil.addAccount(testUser2Token, testUser2)
        authenticationUtil.addAccount(testUser3Token, testUser3)
    }

    private fun addTestAccounts() {
        am.addAccountExplicitly(
            Account(testUser1.login, AccountGeneral.ACCOUNT_TYPE),
            null,
            null
        )
        am.addAccountExplicitly(
            Account(testUser2.login, AccountGeneral.ACCOUNT_TYPE),
            null,
            null
        )
        am.addAccountExplicitly(
            Account(testUser3.login, AccountGeneral.ACCOUNT_TYPE),
            null,
            null
        )
    }
}