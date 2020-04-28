package com.mydigipay.challenge.data.models


import com.google.gson.annotations.SerializedName

data class Verification(
    @SerializedName("payload")
    var payload: Any,
    @SerializedName("reason")
    var reason: String,
    @SerializedName("signature")
    var signature: Any,
    @SerializedName("verified")
    var verified: Boolean
)