package com.mydigipay.challenge.utils

class NetworkErrorEvent(
    val throwable: Throwable,
    val onRetry: (() -> Unit)? = null,
    val onCancle: (() -> Unit)? = null
)


