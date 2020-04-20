package com.mydigipay.challenge.auth

import android.accounts.Account
import android.accounts.AccountManager
import com.mydigipay.challenge.data.models.User


interface AuthenticationUtil {

    /**
     * Get accounts by this project account type from [AccountManager] by [AccountGeneral.ACCOUNT_TYPE]
     * then check authenticationState
     *
     * @return State of Authentication
     */
    fun authenticationState(): AuthenticationState

    /**
     * Add New account
     *
     * @param token access token that get from github
     * @param user object of [User]
     */
    fun addAccount(token: String, user: User)

    /**
     * Clear all accounts by this [AccountGeneral.ACCOUNT_TYPE]
     */
    fun removeAllAccounts()

    /**
     * Clear all accounts by this [AccountGeneral.ACCOUNT_TYPE]
     *
     * @param login user name of account
     */
    fun removeAccount(login: String)

    /**
     * Get current Account from [AccountManager] by [AccountGeneral.ACCOUNT_TYPE]
     *
     * @return Authenticated user or null if user not selected
     */
    fun getCurrentUser(): User?

    /**
     * Get all Account from [AccountManager] by [AccountGeneral.ACCOUNT_TYPE]
     * then convert account to [User]
     *
     * @return Authenticated user
     */
    fun getAllUsers(): List<User>

    /**
     * Return all accounts of thisby
     */
    fun getAllAccounts(): Array<Account>

    /**
     * Get Current user access token from [AccountManager]
     *
     * @return if user selected otherwise null
     */
    fun getAccessToken(): String?

    /**
     * Get selected account from [AccountManager]
     *
     * @return if user selected otherwise null
     */
    fun getCurrentAccount(): Account?
}