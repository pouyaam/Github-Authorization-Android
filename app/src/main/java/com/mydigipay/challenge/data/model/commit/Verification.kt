package com.mydigipay.challenge.data.model.commit

import com.google.gson.annotations.SerializedName

data class Verification(
    @SerializedName("payload")
    val payload: Any? = null,
    @SerializedName("reason")
    val reason: String? = null,
    @SerializedName("signature")
    val signature: Any? = null,
    @SerializedName("verified")
    val verified: Boolean? = null
)