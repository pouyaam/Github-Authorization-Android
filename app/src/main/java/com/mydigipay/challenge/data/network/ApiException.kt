package com.mydigipay.challenge.data.network

import okio.IOException

data class ApiException(override val message: String) : IOException()