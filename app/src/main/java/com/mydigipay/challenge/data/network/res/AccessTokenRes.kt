package com.mydigipay.challenge.data.network.res

import com.google.gson.annotations.SerializedName

data class AccessTokenRes(
    @SerializedName("access_token")
    var accessToken: String,

    @SerializedName("token_type")
    var tokenType: String
)