package com.mydigipay.challenge.dataaccess.model

import com.google.gson.annotations.SerializedName

data class RequestAccessToken(
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

data class ResponseAccessToken(
    @SerializedName("access_token")
    var accessToken: String,
    @SerializedName("token_type")
    var tokenType: String
)