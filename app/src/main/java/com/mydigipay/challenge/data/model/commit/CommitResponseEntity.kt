package com.mydigipay.challenge.data.model.commit

import com.google.gson.annotations.SerializedName
import com.mydigipay.challenge.domain.model.Commit

data class CommitResponseEntity(
    @SerializedName("author")
    val author: RemoteAuthorEntity? = null,
    @SerializedName("comments_url")
    val commentsUrl: String? = null,
    @SerializedName("commit")
    val commit: RemoteCommitEntity? = null,
    @SerializedName("committer")
    val committer: RemoteCommiterEntity? = null,
    @SerializedName("html_url")
    val htmlUrl: String? = null,
    @SerializedName("node_id")
    val nodeId: String? = null,
    @SerializedName("parents")
    val parents: List<RemoteParentEntity>? = null,
    @SerializedName("sha")
    val sha: String? = null,
    @SerializedName("url")
    val url: String? = null
)

fun CommitResponseEntity.mapToDomainModel(): Commit {
    return Commit(
        message = commit?.message,
        author = commit?.author?.mapToDomainModel(),
        commentsCount = commit?.commentCount
    )
}