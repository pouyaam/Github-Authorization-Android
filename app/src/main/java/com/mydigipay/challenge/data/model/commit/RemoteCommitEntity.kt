package com.mydigipay.challenge.data.model.commit

import com.google.gson.annotations.SerializedName

data class RemoteCommitEntity(
    @SerializedName("author")
    val author: RemoteAuthorEntity?,
    @SerializedName("comment_count")
    val commentCount: Int?,
    @SerializedName("committer")
    val committer: RemoteCommiterEntity?,
    @SerializedName("message")
    val message: String?,
    @SerializedName("tree")
    val tree: Tree?,
    @SerializedName("url")
    val url: String?,
    @SerializedName("verification")
    val verification: Verification?
)