package com.mydigipay.challenge.base

import android.os.Bundle
import androidx.annotation.IdRes

interface Navigator {
    fun navigate(@IdRes direction: Int, args: Bundle? = null)
    fun popBackStack(@IdRes direction: Int)
    fun popBackStackTo(@IdRes destination: Int, inclusive: Boolean = false)
}