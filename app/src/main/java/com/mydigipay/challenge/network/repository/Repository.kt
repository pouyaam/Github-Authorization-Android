package com.mydigipay.challenge.network.repository

import com.google.gson.annotations.SerializedName

data class Repository(
    @SerializedName("name")
    val name: String,
    @SerializedName("full_name")
    val fullName: String,
    @SerializedName("commits_url")
    val commitsUrl: String

)