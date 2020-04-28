package com.mydigipay.challenge.data.models


import androidx.recyclerview.widget.DiffUtil
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
) {
    companion object {
        val DIFF_CALLBACK = object : DiffUtil.ItemCallback<CommitDetail>() {
            override fun areItemsTheSame(oldItem: CommitDetail, newItem: CommitDetail): Boolean {
                return oldItem.sha == newItem.sha
            }

            override fun areContentsTheSame(oldItem: CommitDetail, newItem: CommitDetail): Boolean {
                return oldItem == newItem
            }
        }
    }
}