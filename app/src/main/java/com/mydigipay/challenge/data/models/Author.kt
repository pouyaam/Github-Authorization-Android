package com.mydigipay.challenge.data.models


import com.google.gson.annotations.SerializedName

data class Author(
    @SerializedName("date")
    var date: String,
    @SerializedName("email")
    var email: String,
    @SerializedName("name")
    var name: String
)