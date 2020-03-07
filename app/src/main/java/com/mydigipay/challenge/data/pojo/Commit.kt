package com.mydigipay.challenge.data.pojo

import com.google.gson.annotations.SerializedName

data class Commit(
    @SerializedName("sha")
    val sha: String,
    @SerializedName("node_id")
    val nodeId: String,
    @SerializedName("commit")
    val message: Message,
    @SerializedName("committer")
    val committer: Owner
)

data class Message(
    @SerializedName("message")
    val message: String
)