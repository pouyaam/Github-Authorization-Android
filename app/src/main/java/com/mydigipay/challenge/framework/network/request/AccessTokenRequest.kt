package com.mydigipay.challenge.framework.network.request

import com.google.gson.annotations.SerializedName

data class AccessTokenRequest(
    @SerializedName("client_id")
    var clientId: String,

    @SerializedName("client_secret")
    var clientSecret: String,

    @SerializedName("code")
    var code: String,

    @SerializedName("redirect_uri")
    var redirectUri: String,

    @SerializedName("state")
    var state: String
)
