package com.mydigipay.challenge.data.models


import com.google.gson.annotations.SerializedName

sealed class TextTab {
    abstract var text: String
}

data class Branch(
    @SerializedName("name")
    override var text: String,
    @SerializedName("protected")
    var isProtected: Boolean
) : TextTab()