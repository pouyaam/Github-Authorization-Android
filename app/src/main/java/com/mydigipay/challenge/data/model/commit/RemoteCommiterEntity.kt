package com.mydigipay.challenge.data.model.commit

import com.google.gson.annotations.SerializedName

data class RemoteCommiterEntity(
    @SerializedName("date")
    val date: String?= null,
    @SerializedName("email")
    val email: String?= null,
    @SerializedName("name")
    val name: String?= null
)