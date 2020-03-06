package com.mydigipay.challenge.util.ba

import android.widget.TextView
import androidx.annotation.StringRes
import androidx.databinding.BindingAdapter

@BindingAdapter("textRes")
fun TextView.setTextRes(@StringRes text: Int) {
    this.text = context?.getString(text)
}