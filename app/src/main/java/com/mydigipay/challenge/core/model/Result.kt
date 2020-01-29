package com.mydigipay.challenge.core.model

sealed class Result<out T> {
    data class Success<T>(val value: T) : Result<T>()
    data class Failure(val error: ErrorHolder? = null) : Result<Nothing>()
}

inline fun <R, T> Result<T>.fold(
    ifSuccess: (value: T) -> R,
    ifFailure: (failure: ErrorHolder?) -> R
): R {
    return when (this) {
        is Result.Success<T> -> ifSuccess(value)
        is Result.Failure -> ifFailure(error)
    }
}