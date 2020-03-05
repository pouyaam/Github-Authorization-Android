package com.mydigipay.challenge.data.network.api.gitrepo


import com.google.gson.annotations.SerializedName
import com.mydigipay.challenge.data.model.GitRepo

data class ResponseSearchRepository(
    @SerializedName("total_count")
    val totalCount: Int,
    @SerializedName("incomplete_results")
    val incompleteResults: Boolean,
    @SerializedName("items")
    val gitRepos: List<GitRepo>
)