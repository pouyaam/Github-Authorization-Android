package com.mydigipay.challenge.ui

import android.accounts.AccountManager
import androidx.navigation.findNavController
import androidx.preference.PreferenceManager
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.google.common.truth.Truth
import com.mydigipay.challenge.R
import com.mydigipay.challenge.auth.AuthenticationUtil
import com.mydigipay.challenge.auth.AuthenticationUtilImp
import com.mydigipay.challenge.data.setting.SettingBySharedPref
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import testUser1
import testUser1Token
import testUser2
import testUser2Token

@RunWith(AndroidJUnit4::class)
class HomeActivityTest {

    lateinit var authUtil: AuthenticationUtil
    lateinit var cashSetting: SettingBySharedPref

    @get:Rule
    val rule = ActivityScenarioRule(HomeActivity::class.java)

    @Before
    fun setUp() {
        rule.scenario.onActivity {
            val am = AccountManager.get(it)
            cashSetting = SettingBySharedPref(
                PreferenceManager.getDefaultSharedPreferences(it)
            )
            authUtil = AuthenticationUtilImp(am, cashSetting)
            authUtil.removeAllAccounts()
            cashSetting.clearSelectedUserLogin()

        }
    }

    @Test
    fun singleAuthenticate_THEN_navigateToHome() {

        authUtil.addAccount(testUser1Token, testUser1)
        cashSetting.setSelectedUserLogin(testUser1.login)

        rule.scenario.recreate()
        rule.scenario.onActivity {
            val navController = it.findNavController(R.id.nav_host_fragment)

            Truth.assertThat(navController.currentDestination)
                .isNotNull()

            Truth.assertThat(navController.currentDestination!!.id)
                .isEqualTo(R.id.nav_profile)
        }
    }

    @Test
    fun unauthenticated_THEN_navigateToHome() {
        Truth.assertThat(authUtil.getAllAccounts()).hasLength(0)
        rule.scenario.recreate()
        rule.scenario.onActivity {

            val navController = it.findNavController(R.id.nav_host_fragment)

            Truth.assertThat(navController.currentDestination)
                .isNotNull()

            Truth.assertThat(navController.currentDestination!!.id)
                .isEqualTo(R.id.nav_search_repositories)
        }
    }

    @Test
    fun multipleAuthentication_THEN_navigateToRepoList() {
        authUtil.addAccount(testUser1Token, testUser1)
        authUtil.addAccount(testUser2Token, testUser2)
        cashSetting.clearSelectedUserLogin()
        rule.scenario.recreate()
        rule.scenario.onActivity {

            val navController = it.findNavController(R.id.nav_host_fragment)

            Truth.assertThat(navController.currentDestination)
                .isNotNull()

            Truth.assertThat(navController.currentDestination!!.id)
                .isEqualTo(R.id.nav_search_repositories)
        }
    }

    @Test
    fun multipleAuthenticationWithSelected_THEN_navigateToHome() {
        authUtil.addAccount(testUser1Token, testUser1)
        authUtil.addAccount(testUser2Token, testUser2)

        rule.scenario.recreate()
        rule.scenario.onActivity {

            val navController = it.findNavController(R.id.nav_host_fragment)

            Truth.assertThat(navController.currentDestination)
                .isNotNull()

            Truth.assertThat(navController.currentDestination!!.id)
                .isEqualTo(R.id.nav_profile)
        }
    }
}