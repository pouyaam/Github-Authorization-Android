package com.mydigipay.challenge.domain.model

data class RemoteRepository(
    val id: Int,
    val nodeId: String,
    val name: String,
    val fullName: String,
    val remoteRepositoryOwner: RemoteRepositoryOwner,
    val isPrivate: Boolean,
    val htmlUrl: String,
    val description: String,
    val isFork: Boolean,
    val url: String,
    val createdAt: String,
    val updatedAt: String,
    val pushedAt: String,
    val homepage: String,
    val size: Int,
    val stargazersCount: Int,
    val watchersCount: Int,
    val language: String,
    val forksCount: Int,
    val openIssuesCount: Int,
    val masterBranch: String,
    val defaultBranch: String,
    val score: Double
)