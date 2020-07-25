package com.mydigipay.challenge.presentation.model

import com.mydigipay.challenge.domain.model.CommitAuthor

data class CommitItem(
    val message: String,
    val name: String,
    val email: String,
    val date: String,
    val commentsCount: Int
)