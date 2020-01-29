package com.github.mohammadsianaki.core.model

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

inline fun <R1, R2> Result<R1>.flatMap(action: (R1) -> Result<R2>): Result<R2> =
    when (this) {
        is Result.Success -> action(this.value)
        is Result.Failure -> this
    }

inline fun <R1, R2> Result<R1>.map(action: (R1) -> R2): Result<R2> =
    when (this) {
        is Result.Success -> Result.Success(action(this.value))
        is Result.Failure -> this
    }

fun <R, T : R> Result<T>.getOrDefault(defaultValue: R): R {
    return when (this) {
        is Result.Success -> value
        is Result.Failure -> defaultValue
    }
}