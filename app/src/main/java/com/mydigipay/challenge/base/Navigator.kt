package com.mydigipay.challenge.base

import android.os.Bundle
import androidx.annotation.IdRes
import androidx.navigation.NavOptions

interface Navigator {
    fun navigate(@IdRes direction: Int, args: Bundle? = null, navOptions: NavOptions? = null)
    fun popBackStack(@IdRes direction: Int)
    fun popBackStackTo(@IdRes destination: Int, inclusive: Boolean = false)
}