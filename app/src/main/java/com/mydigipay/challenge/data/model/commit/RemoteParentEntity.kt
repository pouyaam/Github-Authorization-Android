package com.mydigipay.challenge.data.model.commit

import com.google.gson.annotations.SerializedName

data class RemoteParentEntity(
    @SerializedName("sha")
    val sha: String?= null,
    @SerializedName("url")
    val url: String?= null
)