package com.mydigipay.challenge.base

class FinishException(message: String?, cause: Throwable? = null) : Exception(message, cause)
class RequiredCodeException(message: String="", cause: Throwable? = null) : Exception(message, cause)
