package com.mydigipay.challenge.util.ktx

import android.view.View

fun View.show() {
    visibility = View.VISIBLE
}

fun View.hide(isGone: Boolean = true) {
    visibility = if (isGone) View.GONE else View.INVISIBLE
}