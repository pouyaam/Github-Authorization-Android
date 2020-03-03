package com.mydigipay.challenge.util

import androidx.lifecycle.LifecycleOwner
import androidx.navigation.NavController
import androidx.navigation.NavDirections
import com.mydigipay.challenge.base.BaseViewModel
import com.mydigipay.challenge.util.livedata.observeEvent

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

fun LifecycleOwner.addNavigatorOn(
    viewModel: BaseViewModel,
    navController: NavController
) {
    viewModel.navigationCommand.observeEvent(this) { command ->
        when (command) {
            is NavigationCommand.To ->
                navController.navigate(command.directions)
            is NavigationCommand.Back ->
                navController.popBackStack()
            is NavigationCommand.BackTo ->
                navController.popBackStack(command.destinationId, command.inclusive)

        }

    }
}