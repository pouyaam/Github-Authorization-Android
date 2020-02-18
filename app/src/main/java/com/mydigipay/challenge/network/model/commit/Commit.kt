package com.mydigipay.challenge.network.model.commit

import com.google.gson.annotations.SerializedName

data class Commit(
    @SerializedName("message")
    val message: String,
    @SerializedName("committer")
    val committer: Committer

)