package com.mydigipay.challenge.network.oauth

import com.google.gson.annotations.SerializedName

data class ResponseAccessToken(
    @SerializedName("access_token")
    var accessToken: String?,

    @SerializedName("token_type")
    var tokenType: String,

    @SerializedName("error")
    var error: String
)