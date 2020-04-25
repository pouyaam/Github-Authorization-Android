package com.mydigipay.challenge.data.models


import com.google.gson.annotations.SerializedName

data class Search<T>(
    @SerializedName("incomplete_results")
    var incompleteResults: Boolean,
    @SerializedName("items")
    var items: List<T>,
    @SerializedName("total_count")
    var totalCount: Int
)