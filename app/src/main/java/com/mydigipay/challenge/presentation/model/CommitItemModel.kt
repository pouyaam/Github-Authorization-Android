package com.mydigipay.challenge.presentation.model

import com.mydigipay.challenge.presentation.core.ItemModel

data class CommitItemModel(
    val message: String,
    val email: String,
    val account: String,
    val date: String
) : ItemModel