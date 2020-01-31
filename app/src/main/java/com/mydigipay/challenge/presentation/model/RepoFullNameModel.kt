package com.mydigipay.challenge.presentation.model

import java.io.Serializable

data class RepoFullNameModel(
    val owner: String,
    val repo: String
) : Serializable