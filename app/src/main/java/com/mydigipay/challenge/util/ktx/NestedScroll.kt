package com.mydigipay.challenge.util.ktx

import androidx.core.widget.NestedScrollView

fun NestedScrollView.onSwipe(block: (state: SwipeState) -> Unit) {
    setOnScrollChangeListener { _: NestedScrollView?, _: Int, scrollY: Int, _: Int, oldScrollY: Int ->
        val dy = scrollY - oldScrollY
        if (dy > 0) block(SwipeState.DOWN)
        else if (dy < 0) block(SwipeState.UP)
    }
}

enum class SwipeState {
    DOWN, UP
}