package com.mydigipay.challenge.util.ba

import android.animation.LayoutTransition
import android.view.View
import android.view.ViewGroup
import androidx.databinding.BindingAdapter
import com.mydigipay.challenge.util.ktx.hide
import com.mydigipay.challenge.util.ktx.show

@BindingAdapter("visibility", "isGone", requireAll = false)
fun View.visibility(isVisible: Boolean?, isGone: Boolean?) {
    if (isVisible == true) show()
    else hide(isGone ?: true)
}

@BindingAdapter("animateLayoutChanges")
fun ViewGroup.animateLayoutChanges(animate: Boolean?) {
    if (animate == true) {
        val lt = LayoutTransition()
        lt.disableTransitionType(LayoutTransition.DISAPPEARING)
        layoutTransition = lt
        layoutTransition.enableTransitionType(LayoutTransition.CHANGING)
    } else
        layoutTransition = null
}