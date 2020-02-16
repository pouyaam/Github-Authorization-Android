package com.mydigipay.challenge.network.repository

import com.google.gson.annotations.SerializedName

data class CommitWrapper(
    @SerializedName("sha")
    val sha: String,
    @SerializedName("commit")
    val commit: Commit,
    @SerializedName("committer")
    val committerUser: User,
    @SerializedName("description")
    val description: String,
    @SerializedName("owner")
    val owner: Owner

)