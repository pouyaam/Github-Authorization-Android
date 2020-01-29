package com.github.mohammadsianaki.core.model

sealed class ErrorHolder(override val message: String) : Throwable(message) {
    data class NetworkConnection(
        override val message: String, val throwable: Throwable
    ) : ErrorHolder(message)

    data class Server(override val message: String, val throwable: Throwable) :
        ErrorHolder(message)

    data class UnExpected(override val message: String) : ErrorHolder(message)
}