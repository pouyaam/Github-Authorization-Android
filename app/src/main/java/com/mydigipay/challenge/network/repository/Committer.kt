package com.mydigipay.challenge.network.repository

import com.google.gson.annotations.SerializedName

data class Committer(
    @SerializedName("name")
    val name: String,
    @SerializedName("email")
    val email: String,
    @SerializedName("date")
    val date: String

)