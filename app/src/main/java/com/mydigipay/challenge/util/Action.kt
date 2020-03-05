package com.mydigipay.challenge.util

import android.net.Uri
import android.view.ViewGroup
import androidx.activity.ComponentActivity
import androidx.annotation.StringRes
import androidx.fragment.app.Fragment
import androidx.lifecycle.LifecycleOwner
import com.mydigipay.challenge.base.BaseViewModel
import com.mydigipay.challenge.util.ktx.openIntent
import com.mydigipay.challenge.util.ktx.showSnackBar
import com.mydigipay.challenge.util.ktx.showToast
import com.mydigipay.challenge.util.livedata.observeEvent

/**
 * A class to handle most used actions inside viewModel
 *
 */
sealed class ActionCommand {
    data class OpenUrl(val action: String?, val type: String?, val uri: Uri?) : ActionCommand()
    data class ShowToast(val message: String, val duration: Int) : ActionCommand()
    data class ShowToastRes(@StringRes val message: Int, val duration: Int) : ActionCommand()
    data class ShowSnackBar(val message: String, val duration: Int) : ActionCommand()
    data class ShowSnackBarRes(@StringRes val message: Int, val duration: Int) : ActionCommand()
}

fun LifecycleOwner.observeActions(
    viewModel: BaseViewModel
) {
    viewModel.actionCommand.observeEvent(this) { command ->
        when (command) {
            is ActionCommand.OpenUrl -> {
                when (this) {
                    is ComponentActivity -> openIntent(command.action, command.type, command.uri)
                    is Fragment -> openIntent(command.action, command.type, command.uri)
                }
            }

            is ActionCommand.ShowToast ->
                when (this) {
                    is ComponentActivity -> showToast(command.message, command.duration)
                    is Fragment -> showToast(command.message, command.duration)
                }

            is ActionCommand.ShowToastRes ->
                when (this) {
                    is ComponentActivity -> showToast(command.message, command.duration)
                    is Fragment -> showToast(command.message, command.duration)
                }

            is ActionCommand.ShowSnackBar ->
                when (this) {
                    is Fragment ->
                        showSnackBar(command.message, command.duration)
                    is ComponentActivity ->
                        findViewById<ViewGroup>(android.R.id.content).showSnackBar(command.message, command.duration)
                }

            is ActionCommand.ShowSnackBarRes ->
                when (this) {
                    is Fragment ->
                        showSnackBar(command.message, command.duration)
                    is ComponentActivity ->
                        findViewById<ViewGroup>(android.R.id.content).showSnackBar(command.message, command.duration)
                }

        }
    }
}