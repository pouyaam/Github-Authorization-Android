package com.mydigipay.challenge.data.models


import androidx.recyclerview.widget.DiffUtil
import com.google.gson.annotations.SerializedName

data class Repo(
    @SerializedName("clone_url")
    var cloneUrl: String,
    @SerializedName("created_at")
    var createdAt: String,
    @SerializedName("default_branch")
    var defaultBranch: String,
    @SerializedName("description")
    var description: String,
    @SerializedName("fork")
    var fork: Boolean,
    @SerializedName("forks")
    var forks: Int,
    @SerializedName("forks_count")
    var forksCount: Int,
    @SerializedName("full_name")
    var fullName: String,
    @SerializedName("has_wiki")
    var hasWiki: Boolean,
    @SerializedName("homepage")
    var homepage: String,
    @SerializedName("html_url")
    var htmlUrl: String,
    @SerializedName("id")
    var id: Int,
    @SerializedName("language")
    var language: String,
    @SerializedName("name")
    var name: String,
    @SerializedName("node_id")
    var nodeId: String,
    @SerializedName("open_issues")
    var openIssues: Int,
    @SerializedName("open_issues_count")
    var openIssuesCount: Int,
    @SerializedName("private")
    var `private`: Boolean,
    @SerializedName("score")
    var score: Double,
    @SerializedName("stargazers_count")
    var stargazersCount: Int,
    @SerializedName("updated_at")
    var updatedAt: String,
    @SerializedName("owner")
    var owner: User,
    @SerializedName("watchers")
    var watchers: Int,
    @SerializedName("watchers_count")
    var watchersCount: Int
) {
    companion object {
        val DIFF_CALLBACK = object : DiffUtil.ItemCallback<Repo>() {
            override fun areItemsTheSame(oldItem: Repo, newItem: Repo): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: Repo, newItem: Repo): Boolean {
                return oldItem == newItem
            }
        }
    }
}