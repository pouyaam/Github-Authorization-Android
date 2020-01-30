package com.mydigipay.challenge.data.api.github

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class SearchResponseDto(
    @SerializedName("total_count")
    @Expose
    val totalCount: Int = 0,
    @SerializedName("incomplete_results")
    @Expose
    val isIncompleteResults: Boolean = false,
    @SerializedName("items")
    @Expose
    val remoteItemEntities: List<RemoteItemEntity>? = null
)
