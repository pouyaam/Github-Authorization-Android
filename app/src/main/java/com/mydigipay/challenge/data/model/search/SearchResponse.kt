package com.mydigipay.challenge.data.model.search

import com.google.gson.annotations.SerializedName
import com.mydigipay.challenge.data.model.search.RemoteRepositorySearchResponse

data class SearchResponse(
    @SerializedName("total_count")
    val totalCount: Int,

    @SerializedName("incomplete_results")
    val isIncompleteResults: Boolean,

    @SerializedName("items")
    val remoteSearchItemEntities: List<RemoteRepositorySearchResponse>
)