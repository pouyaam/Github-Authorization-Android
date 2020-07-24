package com.mydigipay.challenge.data.model.commit

import com.google.gson.annotations.SerializedName

data class Tree(
    @SerializedName("sha")
    val sha: String?,
    @SerializedName("url")
    val url: String?
)