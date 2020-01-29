package com.mydigipay.challenge.core.toplevel

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

suspend fun <T> awaitIO(block: suspend CoroutineScope.() -> T) = withContext(Dispatchers.IO, block)