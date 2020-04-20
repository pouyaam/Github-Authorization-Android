package com.mydigipay.challenge.auth

import android.accounts.Account
import android.accounts.AccountManager
import android.app.Activity
import android.content.SharedPreferences
import android.os.Build
import androidx.preference.PreferenceManager
import androidx.test.core.app.ApplicationProvider
import com.google.common.truth.Truth.assertThat
import com.mydigipay.challenge.data.models.User
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.koin.core.context.stopKoin
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config

@RunWith(RobolectricTestRunner::class)
@Config(sdk = [Build.VERSION_CODES.O_MR1])
class AuthenticationUtilImpTest {
    private lateinit var am: AccountManager
    private lateinit var sharedPreferences: SharedPreferences
    private lateinit var activity: Activity
    private lateinit var authenticationUtil: AuthenticationUtil

    private val user1 = User(
        login = "USER_1",
        avatarUrl = "USER_1_AVATAR_URL",
        bio = "USER_1_BIO",
        email = "USER_1_EMAIL"
    )
    private val user1Token = "USER_1_TOKEN"
    private val user2 = User(
        login = "USER_2",
        avatarUrl = "USER_2_AVATAR_URL",
        bio = "USER_2_BIO",
        email = "USER_2_EMAIL"
    )
    private val user2Token = "USER_2_TOKEN"

    private val user3 = User(
        login = "USER_3",
        avatarUrl = "USER_3_AVATAR_URL",
        bio = "USER_3_BIO",
        email = "USER_3_EMAIL"
    )
    private val user3Token = "USER_3_TOKEN"


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

        authenticationUtil = AuthenticationUtilImp(am, sharedPreferences)
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
        editor.putString(AccountGeneral.CURRENT_USER_NAME_KEY, null)
        editor.commit()

        assertThat(authenticationUtil.getCurrentUser()).isNull()
    }

    @Test
    fun defaultUserIsSelected_THEN_ReturnUser() {
        addTestUser()
        val editor = sharedPreferences.edit()
        editor.putString(AccountGeneral.CURRENT_USER_NAME_KEY, user3.login)
        editor.commit()

        assertThat(authenticationUtil.getCurrentUser()).isNotNull()
        assertThat(authenticationUtil.getCurrentUser()).isEqualTo(user3)
    }

    @Test
    fun getAccessToken() {
        addTestUser()
        val editor = sharedPreferences.edit()
        editor.putString(AccountGeneral.CURRENT_USER_NAME_KEY, user2.login)
        editor.commit()

        assertThat(authenticationUtil.getAccessToken()).isNotNull()
        assertThat(authenticationUtil.getAccessToken()).isEqualTo(user2Token)
    }

    @Test
    fun getAllUsers() {
        addTestUser()

        val allUser = authenticationUtil.getAllUsers()

        assertThat(allUser).hasSize(3)
        assertThat(user1).isIn(allUser)
        assertThat(user2).isIn(allUser)
        assertThat(user3).isIn(allUser)
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
        authenticationUtil.removeAccount(user1.login)

        assertThat(am.getAccountsByType(AccountGeneral.ACCOUNT_TYPE)).hasLength(2)
    }

    private fun addTestUser() {
        authenticationUtil.addAccount(user1Token, user1)
        authenticationUtil.addAccount(user2Token, user2)
        authenticationUtil.addAccount(user3Token, user3)
    }

    private fun addTestAccounts() {
        am.addAccountExplicitly(
            Account(user1.login, AccountGeneral.ACCOUNT_TYPE),
            null,
            null
        )
        am.addAccountExplicitly(
            Account(user2.login, AccountGeneral.ACCOUNT_TYPE),
            null,
            null
        )
        am.addAccountExplicitly(
            Account(user3.login, AccountGeneral.ACCOUNT_TYPE),
            null,
            null
        )
    }
}