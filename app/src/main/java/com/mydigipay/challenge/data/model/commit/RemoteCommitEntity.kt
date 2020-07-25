package com.mydigipay.challenge.data.model.commit

import com.google.gson.annotations.SerializedName

data class RemoteCommitEntity(
    @SerializedName("author")
    val author: RemoteAuthorEntity?= null,
    @SerializedName("comment_count")
    val commentCount: Int?= null,
    @SerializedName("committer")
    val committer: RemoteCommiterEntity?= null,
    @SerializedName("message")
    val message: String?= null,
    @SerializedName("tree")
    val tree: Tree?= null,
    @SerializedName("url")
    val url: String?= null,
    @SerializedName("verification")
    val verification: Verification?= null
)