package com.mydigipay.challenge.data.model.commit

import com.google.gson.annotations.SerializedName

data class RemoteCommiterEntity(
    @SerializedName("date")
    val date: String,
    @SerializedName("email")
    val email: String,
    @SerializedName("name")
    val name: String
)