package com.mydigipay.challenge.framework.network.response

import com.google.gson.annotations.SerializedName

data class GithubRepoResponse(
    @SerializedName("id")
    val id: Int,
    @SerializedName("node_id")
    val nodeId: String,
    @SerializedName("name")
    val name: String,
    @SerializedName("full_name")
    val fullName: String,
    @SerializedName("private")
    val `private`: Boolean,
    @SerializedName("owner")
    val repoOwnerResponse: RepoOwnerResponse,
    @SerializedName("html_url")
    val htmlUrl: String,
    @SerializedName("description")
    val description: String?,
    @SerializedName("fork")
    val fork: Boolean,
    @SerializedName("url")
    val url: String,
    @SerializedName("forks_url")
    val forksUrl: String,
    @SerializedName("keys_url")
    val keysUrl: String,
    @SerializedName("collaborators_url")
    val collaboratorsUrl: String,
    @SerializedName("teams_url")
    val teamsUrl: String,
    @SerializedName("hooks_url")
    val hooksUrl: String,
    @SerializedName("issue_events_url")
    val issueEventsUrl: String,
    @SerializedName("events_url")
    val eventsUrl: String,
    @SerializedName("assignees_url")
    val assigneesUrl: String,
    @SerializedName("branches_url")
    val branchesUrl: String,
    @SerializedName("tags_url")
    val tagsUrl: String,
    @SerializedName("blobs_url")
    val blobsUrl: String,
    @SerializedName("git_tags_url")
    val gitTagsUrl: String,
    @SerializedName("git_refs_url")
    val gitRefsUrl: String,
    @SerializedName("trees_url")
    val treesUrl: String,
    @SerializedName("statuses_url")
    val statusesUrl: String,
    @SerializedName("languages_url")
    val languagesUrl: String,
    @SerializedName("stargazers_url")
    val stargazersUrl: String,
    @SerializedName("contributors_url")
    val contributorsUrl: String,
    @SerializedName("subscribers_url")
    val subscribersUrl: String,
    @SerializedName("subscription_url")
    val subscriptionUrl: String,
    @SerializedName("commits_url")
    val commitsUrl: String,
    @SerializedName("git_commits_url")
    val gitCommitsUrl: String,
    @SerializedName("comments_url")
    val commentsUrl: String,
    @SerializedName("issue_comment_url")
    val issueCommentUrl: String,
    @SerializedName("contents_url")
    val contentsUrl: String,
    @SerializedName("compare_url")
    val compareUrl: String,
    @SerializedName("merges_url")
    val mergesUrl: String,
    @SerializedName("archive_url")
    val archiveUrl: String,
    @SerializedName("downloads_url")
    val downloadsUrl: String,
    @SerializedName("issues_url")
    val issuesUrl: String,
    @SerializedName("pulls_url")
    val pullsUrl: String,
    @SerializedName("milestones_url")
    val milestonesUrl: String,
    @SerializedName("notifications_url")
    val notificationsUrl: String,
    @SerializedName("labels_url")
    val labelsUrl: String,
    @SerializedName("releases_url")
    val releasesUrl: String,
    @SerializedName("deployments_url")
    val deploymentsUrl: String
)
