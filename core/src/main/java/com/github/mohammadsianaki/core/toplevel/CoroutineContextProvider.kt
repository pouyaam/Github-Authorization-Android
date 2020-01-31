package com.github.mohammadsianaki.core.toplevel

import kotlinx.coroutines.Dispatchers
import kotlin.coroutines.CoroutineContext

object CoroutineContextProvider {
    val Main: CoroutineContext = Dispatchers.Main
    val IO: CoroutineContext = Dispatchers.IO
}