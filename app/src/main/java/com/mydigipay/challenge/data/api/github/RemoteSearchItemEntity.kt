package com.mydigipay.challenge.data.api.github

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class RemoteSearchItemEntity(
    @SerializedName("id")
    @Expose
    var id: Int = 0,
    @SerializedName("node_id")
    @Expose
    var nodeId: String? = null,
    @SerializedName("name")
    @Expose
    var name: String? = null,
    @SerializedName("full_name")
    @Expose
    var fullName: String? = null,
    @SerializedName("owner")
    @Expose
    var remoteOwnerEntity: RemoteOwnerEntity? = null,
    @SerializedName("private")
    @Expose
    var isPrivate: Boolean = false,
    @SerializedName("html_url")
    @Expose
    var htmlUrl: String? = null,
    @SerializedName("description")
    @Expose
    var description: String? = null,
    @SerializedName("fork")
    @Expose
    var isFork: Boolean = false,
    @SerializedName("url")
    @Expose
    var url: String? = null,
    @SerializedName("created_at")
    @Expose
    var createdAt: String? = null,
    @SerializedName("updated_at")
    @Expose
    var updatedAt: String? = null,
    @SerializedName("pushed_at")
    @Expose
    var pushedAt: String? = null,
    @SerializedName("homepage")
    @Expose
    var homepage: String? = null,
    @SerializedName("size")
    @Expose
    var size: Int = 0,
    @SerializedName("stargazers_count")
    @Expose
    var stargazersCount: Int = 0,
    @SerializedName("watchers_count")
    @Expose
    var watchersCount: Int = 0,
    @SerializedName("language")
    @Expose
    var language: String? = null,
    @SerializedName("forks_count")
    @Expose
    var forksCount: Int = 0,
    @SerializedName("open_issues_count")
    @Expose
    var openIssuesCount: Int = 0,
    @SerializedName("master_branch")
    @Expose
    var masterBranch: String? = null,
    @SerializedName("default_branch")
    @Expose
    var defaultBranch: String? = null,
    @SerializedName("score")
    @Expose
    var score: Double = 0.0
)