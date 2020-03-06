package com.mydigipay.challenge.util

import androidx.navigation.NavController
import androidx.navigation.NavDirections

infix fun NavController.go(directions: NavDirections) {
    this.navigate(directions)
}