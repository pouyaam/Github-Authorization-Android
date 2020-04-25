package com.mydigipay.challenge.data.repositories

sealed class ApiResult<T> {

    data class Success<T>(val data: T) : ApiResult<T>()

    data class Error<T>(val exception: Exception, var data: T? = null) : ApiResult<T>() {

        lateinit var message: String
        var code: Int = -1

        init {
            handleError(exception)
        }

        private fun handleError(exception: Exception) {

        }
    }
}