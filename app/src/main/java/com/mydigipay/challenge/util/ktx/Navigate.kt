package com.mydigipay.challenge.util.ktx

import android.app.Activity
import android.content.Intent
import android.net.Uri
import androidx.fragment.app.Fragment
import com.mydigipay.challenge.R

fun Activity.openIntent(action: String?, type: String?, uri: Uri?) {
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
                    showToast(R.string.application_not_found)
            }
        }
}

fun Fragment.openIntent(action: String?, type: String?, uri: Uri?) {
    activity?.openIntent(action, type, uri)
}