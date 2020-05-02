package com.mydigipay.challenge.auth

import android.accounts.Account
import android.accounts.AccountManager
import android.os.Build
import android.os.Bundle
import com.mydigipay.challenge.data.models.User
import com.mydigipay.challenge.util.Setting

class AuthenticationUtilImp(
    private val accountManager: AccountManager,
    private val cashSetting: Setting
) : AuthenticationUtil {

    override fun authenticationState(): AuthenticationState {
        val accounts = getAllAccounts();
        return when (accounts.size) {
            0 -> {
                AuthenticationState.UNAUTHENTICATED
            }
            1 -> {
                AuthenticationState.SINGLE_AUTHENTICATION
            }
            else -> {
                if (cashSetting.isDefaultUserSelected())
                    AuthenticationState.SINGLE_AUTHENTICATION
                else
                    AuthenticationState.MULTIPLE_AUTHENTICATION
            }

        }
    }

    override fun addAccount(token: String, user: User) {
        val account = Account(user.login, AccountGeneral.ACCOUNT_TYPE)

        val userData = Bundle()
        userData.putString("AVATAR_URL", user.avatarUrl)
        userData.putString("BIO", user.bio)
        userData.putString("EMAIL", user.email)
        user.following?.let { userData.putInt("FOLLOWING", it) }
        user.followers?.let { userData.putInt("FOLLOWERS", it) }
        user.publicRepos?.let { userData.putInt("PUBLIC_REPOS", it) }

        accountManager.addAccountExplicitly(account, null, userData)
        accountManager.setAuthToken(account, AccountGeneral.AUTH_TOKEN_TYPE_FULL_ACCESS, token)

        if (getAllAccounts().size == 1)
            cashSetting.setSelectedUserLogin(user.login)

    }

    override fun getCurrentUser(): User? = convertAccountToUser(getCurrentAccount())

    override fun getCurrentAccount(): Account? {
        val accounts = getAllAccounts()

        if (accounts.size == 1)
            return accounts[0]

        return cashSetting.getSelectedUserLogin()
            .takeIf { (it != null && it != "") }
            .run {
                getAllAccounts().firstOrNull() { it.name == this }
            }
    }

    override fun getAllUsers(): List<User> = getAllAccounts().map { convertAccountToUser(it)!! }

    private fun convertAccountToUser(account: Account?): User? {
        return if (account != null)
            User(
                login = account.name,
                avatarUrl = accountManager.getUserData(account, "AVATAR_URL"),
                bio = accountManager.getUserData(account, "BIO"),
                email = accountManager.getUserData(account, "EMAIL"),
                followers = (accountManager.getUserData(account, "FOLLOWING") ?: "0").toInt(),
                following = (accountManager.getUserData(account, "FOLLOWERS") ?: "0").toInt(),
                publicRepos = (accountManager.getUserData(account, "PUBLIC_REPOS") ?: "0").toInt()
            )
        else
            null

    }

    override fun getAccessToken(): String? = getCurrentAccount()?.let {
        accountManager.peekAuthToken(it, AccountGeneral.AUTH_TOKEN_TYPE_FULL_ACCESS)
    }

    override fun removeAllAccounts() {
        getAllAccounts().forEach {
            removeAccount(it)
        }
        cashSetting.clearSelectedUserLogin()
    }

    private fun removeAccount(account: Account) {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP_MR1) {
            accountManager.removeAccount(account, null, null)
        } else
            accountManager.removeAccountExplicitly(account)
    }

    override fun removeAccount(login: String) {
        getAllAccounts().firstOrNull { it.name == login }?.let { removeAccount(it) }
    }

    override fun getAllAccounts(): Array<Account> =
        accountManager.getAccountsByType(AccountGeneral.ACCOUNT_TYPE)
}