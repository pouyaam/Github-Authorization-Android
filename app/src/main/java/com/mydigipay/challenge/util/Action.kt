package com.mydigipay.challenge.util

import android.net.Uri
import androidx.activity.ComponentActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.LifecycleOwner
import com.mydigipay.challenge.base.BaseViewModel
import com.mydigipay.challenge.util.ktx.openIntent
import com.mydigipay.challenge.util.livedata.observeEvent

/**
 * A class to handle most used actions inside viewModel
 *
 */
sealed class ActionCommand {
    data class OpenUrl(val action: String?, val type: String?, val uri: Uri?) : ActionCommand()
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
        }
    }
}