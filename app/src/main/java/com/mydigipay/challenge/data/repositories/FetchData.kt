package com.mydigipay.challenge.data.repositories

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

suspend fun <T> fetchFromNetwork(
    apiFun: suspend () -> T,
    dbFun: (suspend () -> T)? = null
): ApiResult<T> =
    withContext(Dispatchers.IO) {
        try {
            ApiResult.Success(apiFun())
        } catch (e: Exception) {
            val error = ApiResult.Error<T>(e)
            dbFun?.let {
                error.data = try {
                    error.data
                } finally {
                    // TODO: 4/25/20 handleDbError
                }
            }
            error
        }
    }

suspend fun <T> fetchFromDB(
    dbFun: (suspend () -> T)
): ApiResult<T> =
    withContext(Dispatchers.IO) {
        try {
            ApiResult.Success(dbFun())
        } catch (e: Exception) {
            ApiResult.Error(e)

        }
    }