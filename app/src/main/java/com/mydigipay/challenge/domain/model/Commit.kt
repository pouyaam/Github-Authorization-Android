package com.mydigipay.challenge.domain.model

import com.mydigipay.challenge.presentation.model.CommitItem

data class Commit(
    val message: String?,
    val author: CommitAuthor?,
    val commentsCount: Int?
)

fun Commit.mapToPresentationModel(): CommitItem {
    return CommitItem(
        message = message ?: "",
        commentsCount = commentsCount ?: 0,
        email = author?.email ?: "",
        date = author?.date ?: "",
        name = author?.name ?: ""
    )
}