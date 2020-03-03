package com.mydigipay.challenge.util.ktx

import android.content.Context
import android.widget.Toast
import androidx.annotation.StringRes

/**
 * Shows a toast message with the help of a context reference
 *
 * @param message: The showing message as string
 * @param duration: defaults to SHORT. Will pass it to [Toast][android.widget.Toast]
 */
fun Context.showToast(message: String, duration: Int = Toast.LENGTH_SHORT) {
    Toast.makeText(this, message, duration).show()
}

/**
 * Shows a toast message with the help of a context reference
 *
 * @param message: The showing message as string
 * @param duration: defaults to SHORT. Will pass it to [Toast][android.widget.Toast]
 */
fun Context.showToast(@StringRes message: Int, duration: Int = Toast.LENGTH_SHORT) {
    Toast.makeText(this, message, duration).show()
}