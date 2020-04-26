package com.mydigipay.challenge.data.repositories

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import timber.log.Timber

suspend fun <T> fetchFromNetwork(
    apiFun: suspend () -> T,
    dbFun: (suspend () -> T)? = null
): ApiResult<T> =
    withContext(Dispatchers.IO) {
        try {
            ApiResult.Success(apiFun())
        } catch (e: Exception) {

            Timber.e(e)

            val error = ApiResult.Error<T>(e)
            dbFun?.let {
                error.data = try {
                    error.data
                } finally {
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
            Timber.e(e)
            ApiResult.Error<T>(e)
        }
    }