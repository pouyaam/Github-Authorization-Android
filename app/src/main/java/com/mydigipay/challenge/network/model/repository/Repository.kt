package com.mydigipay.challenge.network.model.repository

import com.google.gson.annotations.SerializedName
import com.mydigipay.challenge.network.model.user.User

data class Repository(
    @SerializedName("name")
    val name: String,
    @SerializedName("full_name")
    val fullName: String,
    @SerializedName("description")
    val description: String,
    @SerializedName("owner")
    val owner: User

)