package com.mydigipay.challenge.utils.ktx

import android.app.Activity
import android.content.Intent
import android.net.Uri

fun Activity.openUrl(url: String) {
    Intent(Intent.ACTION_VIEW, Uri.parse(url)).also { intent ->
        packageManager?.let {
            if (intent.resolveActivity(it) != null)
                startActivity(intent)
        }
    }
}
