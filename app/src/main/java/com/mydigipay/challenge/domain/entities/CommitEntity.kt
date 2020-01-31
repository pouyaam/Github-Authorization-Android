package com.mydigipay.challenge.domain.entities

data class CommitEntity(
    val message: String,
    val author: CommitAuthorEntity,
    val commentsCount: Int
)