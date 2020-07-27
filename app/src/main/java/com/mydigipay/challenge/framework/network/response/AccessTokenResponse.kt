package com.mydigipay.challenge.framework.network.response

import com.google.gson.annotations.SerializedName

data class AccessTokenResponse(
    @SerializedName("access_token")
    var accessToken: String,

    @SerializedName("token_type")
    var tokenType: String
)
