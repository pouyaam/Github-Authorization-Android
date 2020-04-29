package com.mydigipay.challenge.data.models


import androidx.recyclerview.widget.DiffUtil
import com.google.gson.annotations.SerializedName

data class CommitDetail(
    @SerializedName("commit")
    var commit: Commit? = null,
    @SerializedName("committer")
    var committer: User? = null,
    @SerializedName("html_url")
    var htmlUrl: String? = null,
    @SerializedName("node_id")
    var nodeId: String? = null,
    @SerializedName("sha")
    var sha: String? = null,
    @SerializedName("url")
    var url: String? = null
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