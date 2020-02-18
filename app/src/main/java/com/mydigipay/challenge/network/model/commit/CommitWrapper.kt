package com.mydigipay.challenge.network.model.commit

import com.google.gson.annotations.SerializedName
import com.mydigipay.challenge.network.model.user.User

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
    val owner: User

)