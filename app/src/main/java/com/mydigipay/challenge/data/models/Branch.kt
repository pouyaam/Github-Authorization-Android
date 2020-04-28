package com.mydigipay.challenge.data.models


import com.google.gson.annotations.SerializedName

data class Branch(
    @SerializedName("name")
    var name: String,
    @SerializedName("protected")
    var `protected`: Boolean
)