package com.mydigipay.challenge.utils

import androidx.navigation.NavDirections


sealed class NavigationCommand {
  data class To(val directions: NavDirections): NavigationCommand()
  data class ToWithFinish(val directions: NavDirections): NavigationCommand()
  data class ToAction(val actionId: Int): NavigationCommand()
  data class ToActionWithFinish(val actionId: Int): NavigationCommand()
  object Back: NavigationCommand()
  data class BackTo(val destinationId: Int, val inclusive: Boolean): NavigationCommand()
  object ToRoot: NavigationCommand()
}