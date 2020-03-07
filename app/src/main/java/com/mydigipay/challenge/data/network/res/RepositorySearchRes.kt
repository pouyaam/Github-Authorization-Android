package com.mydigipay.challenge.data.network.res


import com.google.gson.annotations.SerializedName
import com.mydigipay.challenge.data.pojo.Repository

data class RepositorySearchRes(
    @SerializedName("total_count")
    val totalCount: Int,
    @SerializedName("incomplete_results")
    val incompleteResults: Boolean,
    @SerializedName("items")
    val gitRepos: List<Repository>
)