package com.mydigipay.challenge.presentation.model

data class RepositoryItem(
    var id: Int,
    var nodeId: String,
    var name: String,
    var fullName: String,
    var repoOwnerItem: RepositoryOwnerItem?,
    var isPrivate: Boolean,
    var htmlUrl: String,
    var description: String,
    var isFork: Boolean,
    var url: String,
    var createdAt: String,
    var updatedAt: String,
    var pushedAt: String,
    var homepage: String,
    var size: Int,
    var stargazersCount: Int,
    var watchersCount: Int,
    var language: String,
    var forksCount: Int,
    var openIssuesCount: Int,
    var masterBranch: String,
    var defaultBranch: String,
    var score: Double
)