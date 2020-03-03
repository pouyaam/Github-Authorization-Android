package com.mydigipay.challenge.util

import androidx.navigation.NavDirections

/**
 * A class to implement command pattern for navigation process
 *
 * To() with the param directions (NavDirections) devs using this call *FragmentDirections action
 *
 */
sealed class NavigationCommand {
    data class To(val directions: NavDirections) : NavigationCommand()
    object Back : NavigationCommand()
    data class BackTo(val destinationId: Int, val inclusive: Boolean) : NavigationCommand()
}