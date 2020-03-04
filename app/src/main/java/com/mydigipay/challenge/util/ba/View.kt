package com.mydigipay.challenge.util.ba

import android.view.View
import androidx.databinding.BindingAdapter
import com.mydigipay.challenge.util.ktx.hide
import com.mydigipay.challenge.util.ktx.show

@BindingAdapter("visibility", "isGone", requireAll = false)
fun View.visibility(isVisible: Boolean?, isGone: Boolean?) {
    if (isVisible == true) show()
    else hide(isGone ?: true)

}