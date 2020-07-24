package com.mydigipay.challenge.data.model.commit

import com.google.gson.annotations.SerializedName
import com.mydigipay.challenge.domain.model.CommitAuthor

data class RemoteAuthorEntity(
    @SerializedName("date")
    val date: String?,
    @SerializedName("email")
    val email: String?,
    @SerializedName("name")
    val name: String?
)

fun RemoteAuthorEntity.mapToDomainModel(): CommitAuthor {
    return CommitAuthor(
        name = name ?: "",
        email = email ?: "",
        date = date ?: ""
    )
}
