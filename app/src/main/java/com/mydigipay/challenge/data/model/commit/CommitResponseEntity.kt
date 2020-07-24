package com.mydigipay.challenge.data.model.commit

import com.google.gson.annotations.SerializedName
import com.mydigipay.challenge.domain.model.Commit

data class CommitResponseEntity(
    @SerializedName("author")
    val author: RemoteAuthorEntity?,
    @SerializedName("comments_url")
    val commentsUrl: String?,
    @SerializedName("commit")
    val commit: RemoteCommitEntity?,
    @SerializedName("committer")
    val committer: RemoteCommiterEntity?,
    @SerializedName("html_url")
    val htmlUrl: String?,
    @SerializedName("node_id")
    val nodeId: String?,
    @SerializedName("parents")
    val parents: List<RemoteParentEntity>?,
    @SerializedName("sha")
    val sha: String?,
    @SerializedName("url")
    val url: String?
)

fun CommitResponseEntity.mapToDomainModel(): Commit {
    return Commit(
        message = commit?.message ?: "",
        author = commit?.author?.mapToDomainModel(),
        commentsCount = commit?.commentCount ?: 0
    )
}