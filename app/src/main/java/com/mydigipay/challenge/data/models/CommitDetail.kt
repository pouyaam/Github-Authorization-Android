package com.mydigipay.challenge.data.models


import com.google.gson.annotations.SerializedName

data class CommitDetail(
    @SerializedName("commit")
    var commit: Commit,
    @SerializedName("committer")
    var committer: User,
    @SerializedName("html_url")
    var htmlUrl: String,
    @SerializedName("node_id")
    var nodeId: String,
    @SerializedName("sha")
    var sha: String,
    @SerializedName("url")
    var url: String
)