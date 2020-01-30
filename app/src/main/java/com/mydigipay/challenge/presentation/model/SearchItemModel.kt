package com.mydigipay.challenge.presentation.model

import com.mydigipay.challenge.presentation.core.ItemModel

data class SearchItemModel(
    var id: Int = 0,
    var nodeId: String? = null,
    var name: String? = null,
    var fullName: String? = null,
    var searchOwnerModel: SearchOwnerModel? = null,
    var isPrivate: Boolean = false,
    var htmlUrl: String? = null,
    var description: String? = null,
    var isFork: Boolean = false,
    var url: String? = null,
    var createdAt: String? = null,
    var updatedAt: String? = null,
    var pushedAt: String? = null,
    var homepage: String? = null,
    var size: Int = 0,
    var stargazersCount: Int = 0,
    var watchersCount: Int = 0,
    var language: String? = null,
    var forksCount: Int = 0,
    var openIssuesCount: Int = 0,
    var masterBranch: String? = null,
    var defaultBranch: String? = null,
    var score: Double = 0.0
) : ItemModel