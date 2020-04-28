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

fun TabLayout.addNewTab(text: String) {
    addTab(newTab().setText(text))
}