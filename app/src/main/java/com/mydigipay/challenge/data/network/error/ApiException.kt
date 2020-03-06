package com.mydigipay.challenge.data.network.error

import okio.IOException

data class ApiException(override val message: String) : IOException()