package com.mydigipay.challenge.data.network.error

import okio.IOException

data class ApiException(
    override val message: String,
    val type: ErrorType,
    val code: Int? = null
) : IOException()

enum class ErrorType {
    TIMEOUT, NETWORK, CLIENT, SERVER, UNKNOWN
}