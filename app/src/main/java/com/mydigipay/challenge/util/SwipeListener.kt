package com.mydigipay.challenge.util

import android.os.Handler
import android.os.Looper
import androidx.recyclerview.widget.RecyclerView


class SwipeListener(
    private val onSwipe: (state: SwipeState) -> Unit
) : RecyclerView.OnScrollListener() {

    private var isUserScrolling = false

    var lockCallback = false

    private val handler = Handler(Looper.getMainLooper())

    private val runnable = Runnable {
        lockCallback = false
    }

    override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
        super.onScrolled(recyclerView, dx, dy)
        if (isUserScrolling && !lockCallback) {
            if (dy < 0) {
                onSwipe(SwipeState.UP)
            } else {
                onSwipe(SwipeState.DOWN)
            }
            lockCallback()
        }
    }

    private fun lockCallback() {
        lockCallback = true
        handler.removeCallbacks(runnable)
        handler.postDelayed(runnable, 1000L)
    }

    override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
        super.onScrollStateChanged(recyclerView, newState)
        isUserScrolling = when (newState) {
            RecyclerView.SCROLL_STATE_DRAGGING -> true
            else -> false
        }
    }

    enum class SwipeState {
        DOWN, UP
    }
}

inline fun RecyclerView.onSwipe(crossinline block: (state: SwipeListener.SwipeState) -> Unit) {
    addOnScrollListener(SwipeListener { block(it) })
}