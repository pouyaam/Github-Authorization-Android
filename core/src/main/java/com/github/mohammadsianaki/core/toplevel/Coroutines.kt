package com.github.mohammadsianaki.core.toplevel

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.withContext

suspend fun <T> awaitIO(block: suspend CoroutineScope.() -> T) = withContext(CoroutineContextProvider.IO, block)