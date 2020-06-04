package com.mydigipay.challenge.infrastructure.data.model.api.repositorysearch


import com.google.gson.annotations.SerializedName

data class ResponseRepositorySearch(
    @SerializedName("incomplete_results")
    val incompleteResults: Boolean?,
    @SerializedName("items")
    val items: MutableList<ItemRepository>?,
    @SerializedName("total_count")
    val totalCount: Int?
)