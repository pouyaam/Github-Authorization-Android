package com.mydigipay.challenge.network.oauth

import com.google.gson.annotations.SerializedName
import com.mydigipay.challenge.app.App.Companion.CLIENT_ID
import com.mydigipay.challenge.app.App.Companion.CLIENT_SECRET
import com.mydigipay.challenge.app.App.Companion.REDIRECT_URI
import com.mydigipay.challenge.utils.githubCode

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
) {
    companion object {
        val DEFAULT = RequestAccessToken(CLIENT_ID, CLIENT_SECRET, githubCode!!, REDIRECT_URI, "0")
    }
}