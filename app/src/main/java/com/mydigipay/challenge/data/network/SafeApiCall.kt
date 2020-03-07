package com.mydigipay.challenge.data.network

import com.mydigipay.challenge.data.network.error.ApiException
import com.mydigipay.challenge.data.network.error.handleError
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext


sealed class ApiResult<out T : Any> {

    data class Success<out T : Any>(val data: T) : ApiResult<T>()
    data class Error(val error: ApiException) : ApiResult<Nothing>()

}

suspend fun <T : Any> safeApiCall(
    call: suspend () -> ApiResult<T>
): ApiResult<T> =
    withContext(Dispatchers.IO) {
        try {
            call()
        } catch (e: Exception) {
            e.printStackTrace()
            ApiResult.Error(handleError(e))
        }
    }