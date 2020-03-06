package com.mydigipay.challenge.dataaccess.model

import com.google.gson.annotations.SerializedName

data class ResponseProject(
    @SerializedName("total_count")
    val totalCount: Int = 0,

    @SerializedName("incomplete_results")
    val isIncompleteResults: Boolean = false,

    @SerializedName("items")
    val remoteSearchItemEntities: List<ResponseProjectItem>? = null
)
