package com.mydigipay.challenge.model

/**
 * Reference:
 * [https://developer.android.com/jetpack/docs/guide#addendum]
 */

class Resource<T> constructor(val status: Status, val data: T?, val error: Throwable? = null) {

    companion object {

        fun <T> loading(): Resource<T> =
            Resource(Status.LOADING, null)

        fun <T> success(data: T): Resource<T> {
            return Resource(Status.SUCCESS, data)
        }

        fun <T> error(throwable: Throwable): Resource<T> {
            return Resource(
                status = Status.ERROR,
                data = null,
                error = throwable
            )
        }
    }
}
