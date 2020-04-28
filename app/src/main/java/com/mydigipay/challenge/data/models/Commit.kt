package com.mydigipay.challenge.data.models


import com.google.gson.annotations.SerializedName

data class Commit(

    @SerializedName("author")
    var author: Author,

    @SerializedName("comment_count")
    var commentCount: Int,

    @SerializedName("message")
    var message: String,

    @SerializedName("url")
    var url: String,

    @SerializedName("verification")
    var verification: Verification
)