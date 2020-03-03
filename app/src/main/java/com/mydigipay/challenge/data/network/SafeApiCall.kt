package com.mydigipay.challenge.data.network

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import okio.IOException
import retrofit2.HttpException
import java.net.SocketTimeoutException


sealed class ApiResult<out T : Any> {

    data class Success<out T : Any>(val data: T) : ApiResult<T>()
    data class Error(val message: String, val errorType: ErrorType) : ApiResult<Nothing>()

}

enum class ErrorType {
    TIMEOUT, NETWORK, CUSTOM, UNKNOWN
}

private fun getErrorType(e: Exception) = when (e) {
    is HttpException -> ErrorType.CUSTOM
    is IOException -> ErrorType.NETWORK
    is SocketTimeoutException -> ErrorType.TIMEOUT
    else -> ErrorType.UNKNOWN
}

suspend fun <T : Any> safeApiCall(
    call: suspend () -> ApiResult<T>
): ApiResult<T> =
    withContext(Dispatchers.IO) {
        try {
            call()
        } catch (e: Exception) {
            e.printStackTrace()
            val message = "Unknown Error" /* TODO: parse from response or get from function */
            ApiResult.Error(message, getErrorType(e))
        }
    }