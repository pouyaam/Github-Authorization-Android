package com.mydigipay.challenge.network.oauth

import com.google.gson.annotations.SerializedName
import com.mydigipay.challenge.app.App.Companion.REDIRECT_URI
import com.mydigipay.challenge.utils.githubClinetId
import com.mydigipay.challenge.utils.githubClinetSecret
import com.mydigipay.challenge.utils.githubCode

data class RequestAccessToken(
    @SerializedName("client_id")
    var clientId: String,

    @SerializedName("client_secret")
    var clientSecret: String,

    @SerializedName("code")
    var code: String?,

    @SerializedName("redirect_uri")
    var redirectUri: String,

    @SerializedName("state")
    var state: String
) {
    companion object {
        val DEFAULT = RequestAccessToken(githubClinetId!!, githubClinetSecret!!, githubCode, REDIRECT_URI, "0")
    }
}