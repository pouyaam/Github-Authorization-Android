package com.mydigipay.challenge.extentions

import android.widget.Toast
import androidx.fragment.app.Fragment

fun Fragment.showToast(
    text: CharSequence,
    duration: Int = Toast.LENGTH_SHORT
) {
    Toast.makeText(context!!, text, duration).show()
}

fun Fragment.showToast(resId: Int, duration: Int = Toast.LENGTH_SHORT) {
    context!!.showToast(resId, duration)
}
