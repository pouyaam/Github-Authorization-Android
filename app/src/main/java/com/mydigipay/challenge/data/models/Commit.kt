package com.mydigipay.challenge.data.models


import com.google.gson.annotations.SerializedName

data class Commit(

    @SerializedName("author")
    var author: Author? = null,

    @SerializedName("comment_count")
    var commentCount: Int? = null,

    @SerializedName("message")
    var message: String? = null,

    @SerializedName("url")
    var url: String? = null,

    @SerializedName("verification")
    var verification: Verification? = null
)