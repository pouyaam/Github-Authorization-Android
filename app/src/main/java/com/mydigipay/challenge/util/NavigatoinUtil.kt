package com.mydigipay.challenge.util

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.NavDirections
import androidx.navigation.NavOptions

infix fun NavController.go(directions: NavDirections) {
    this.navigate(directions)
}

fun NavController.go(directions: NavDirections, navOptions: NavOptions) {
    this.navigate(directions, navOptions)
}

fun Fragment.openIntent(action: String? = null, type: String? = null, uri: Uri? = null) {
    activity?.openIntent(action, type, uri)
}

fun Activity.openIntent(action: String? = null, type: String? = null, uri: Uri? = null) {
    Intent()
        .apply {
            action?.let { this.action = it }
            uri?.let { this.data = it }
            type?.let { this.type = it }
        }
        .also { intent ->
            packageManager?.let {
                if (intent.resolveActivity(it) != null)
                    startActivity(intent)
                else
                    Toast.makeText(this, "application not found", Toast.LENGTH_LONG).show()
            }
        }
}