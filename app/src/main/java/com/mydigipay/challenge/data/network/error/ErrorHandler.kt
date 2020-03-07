package com.mydigipay.challenge.data.network.error

import com.mydigipay.challenge.data.network.error.ErrorType.*
import okio.IOException
import retrofit2.HttpException
import java.net.SocketTimeoutException
import java.net.UnknownHostException

fun handleError(e: Exception): ApiException =
    when (e) {
        is UnknownHostException -> ApiException("Internet is not available", NETWORK)
        is HttpException -> {
            when (e.code()) {
                400 -> ApiException(
                    "The request that was sent to the server is invalid.",
                    CLIENT,
                    e.code()
                )
                401 -> ApiException(
                    "Unauthorized error, you must provide credentials to be able to view the protected resource",
                    CLIENT,
                    e.code()
                )
                403 -> ApiException(
                    "Forbidden error, due to a lack of permission to access the requested resource",
                    CLIENT,
                    e.code()
                )
                404 -> ApiException(
                    "Resource not found",
                    CLIENT,
                    e.code()
                )
                405 -> ApiException(
                    "Method Not Allowed, a request method is not supported for the requested resource",
                    CLIENT,
                    e.code()
                )
                409 -> ApiException(
                    "Conflict error, the request could not be processed because of conflict in the current state of the resource",
                    CLIENT,
                    e.code()
                )
                429 -> ApiException(
                    "Too Many Requests, you have sent too many requests in a given amount of time. Please try again later",
                    CLIENT,
                    e.code()
                )
                500 -> ApiException(
                    "Internal Server Error, server cannot process the request for an unknown reason",
                    SERVER,
                    e.code()
                )
                502 -> ApiException(
                    "Bad Gateway error",
                    SERVER,
                    e.code()
                )
                503 -> ApiException(
                    "Service Unavailable error, the server is overloaded or under maintenance",
                    SERVER,
                    e.code()
                )
                504 -> ApiException(
                    "Gateway Timeout error",
                    SERVER,
                    e.code()
                )
                else -> ApiException(
                    "Unknown HTTP error, please try again",
                    UNKNOWN,
                    e.code()
                )
            }
        }
        is UnAuthorizedException -> ApiException(
            "Unauthorized error, you must provide credentials to be able to view the protected resource",
            CLIENT,
            401
        )
        is SocketTimeoutException -> ApiException("Request timeout!", TIMEOUT)
        is IOException -> ApiException("Unknown Network error, please try again", NETWORK)
        is CustomException -> ApiException(e.message, UNKNOWN)
        else -> ApiException("Unknown error, please try again", UNKNOWN)
    }