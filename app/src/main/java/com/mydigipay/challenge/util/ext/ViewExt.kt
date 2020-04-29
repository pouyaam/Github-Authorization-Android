package com.mydigipay.challenge.util.ext

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import com.google.android.material.tabs.TabLayout
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

fun LiveData<String>.autoComplete(
    lifecycleOwner: LifecycleOwner,
    delay: Long = 0,
    callback: (s: String) -> Unit
) {
    observe(lifecycleOwner, Observer {
        lifecycleOwner.lifecycleScope.launch(Dispatchers.IO) {
            delay(delay)
            callback(it)
        }
    })
}

fun TabLayout.onTabSelected(callback: (tab: TabLayout.Tab?) -> Unit) {
    addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
        override fun onTabReselected(tab: TabLayout.Tab?) {
        }

        override fun onTabUnselected(tab: TabLayout.Tab?) {
        }

        override fun onTabSelected(tab: TabLayout.Tab?) {
            callback(tab)
        }
    })
}