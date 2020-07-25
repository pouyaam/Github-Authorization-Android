package com.mydigipay.challenge.data.model.search

import com.google.gson.annotations.SerializedName

data class SearchResponse(
    @SerializedName("total_count")
    val totalCount: Int? = 0,

    @SerializedName("incomplete_results")
    val isIncompleteResults: Boolean? = false,

    @SerializedName("items")
    val remoteSearchItemEntities: List<RemoteRepositoryEntity>? = null
)