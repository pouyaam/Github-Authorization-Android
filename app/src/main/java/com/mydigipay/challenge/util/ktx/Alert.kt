package com.mydigipay.challenge.util.ktx

import android.content.Context
import android.view.View
import android.widget.Toast
import androidx.annotation.StringRes
import androidx.fragment.app.Fragment
import com.google.android.material.snackbar.Snackbar

/**
 * Shows a toast message with the help of a context reference
 *
 * @param message: The showing message as string
 * @param duration: defaults to SHORT. Will pass it to [Toast][android.widget.Toast]
 */
fun Context.showToast(message: String, duration: Int = Toast.LENGTH_SHORT) =
    Toast.makeText(this, message, duration).show()

fun Fragment.showToast(message: String, duration: Int = Toast.LENGTH_SHORT) =
    context?.showToast(message, duration)

/**
 * Shows a toast message with the help of a context reference
 *
 * @param message: The showing message as string
 * @param duration: defaults to SHORT. Will pass it to [Toast][android.widget.Toast]
 */
fun Context.showToast(@StringRes message: Int, duration: Int = Toast.LENGTH_SHORT) =
    Toast.makeText(this, message, duration).show()

fun Fragment.showToast(@StringRes message: Int, duration: Int = Toast.LENGTH_SHORT) =
    context?.showToast(message, duration)

/**
 * Shows a snackBar with the help of a view reference
 * There should be a named view in xml to get it by kotlin synthetics and call this function on it
 *
 * @param message: The string.xml id of the showing message
 * @param duration: defaults to SHORT. Will pass it to [Snackbar][com.google.android.material.snackbar.Snackbar]
 *
 */
fun View.showSnackBar(
    @StringRes message: Int,
    duration: Int = Snackbar.LENGTH_SHORT
) =
    Snackbar.make(this, message, duration).show()

fun Fragment.showSnackBar(
    @StringRes message: Int,
    duration: Int = Snackbar.LENGTH_SHORT
) =
    view?.showSnackBar(message, duration)

/**
 * Shows a snackBar with the help of a view reference
 * There should be a named view in xml to get it by kotlin synthetics and call this function on it
 *
 * @param message: The showing message as string
 * @param duration: defaults to SHORT. Will pass it to [Snackbar][com.google.android.material.snackbar.Snackbar]
 *
 */
fun View.showSnackBar(
    message: String,
    duration: Int = Snackbar.LENGTH_SHORT
) =
    Snackbar.make(this, message, duration).show()

fun Fragment.showSnackBar(
    message: String,
    duration: Int = Snackbar.LENGTH_SHORT
) =
    view?.showSnackBar(message, duration)