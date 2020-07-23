package com.mydigipay.challenge.data.model

import com.google.gson.annotations.SerializedName

data class SearchResponse(
    @SerializedName("total_count")
    val totalCount: Int,

    @SerializedName("incomplete_results")
    val isIncompleteResults: Boolean,

    @SerializedName("items")
    val remoteSearchItemEntities: List<RemoteRepositorySearchResponse>
)