package com.mydigipay.challenge.network.model.search

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class SearchResponse(
    @SerializedName("total_count")
    @Expose
    val totalCount: Int = 0,
    @SerializedName("incomplete_results")
    @Expose
    val isIncompleteResults: Boolean = false,
    @SerializedName("items")
    @Expose
    val remoteSearchItemEntities: List<SearchItem>? = null
)
