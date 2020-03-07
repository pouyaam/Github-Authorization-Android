package com.mydigipay.challenge.data.pojo


import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import com.mydigipay.challenge.utils.ktx.toEpochMillis
import com.mydigipay.challenge.utils.ktx.toHumanReadableFormat
import com.mydigipay.challenge.utils.ktx.toRelativeTimeFormat
import kotlinx.android.parcel.IgnoredOnParcel
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Repository(
    @SerializedName("id")
    val id: Int,
    @SerializedName("node_id")
    val nodeId: String,
    @SerializedName("name")
    val name: String,
    @SerializedName("full_name")
    val fullName: String,
    @SerializedName("owner")
    val owner: Owner,
    @SerializedName("private")
    val isPrivate: Boolean,
    @SerializedName("html_url")
    val htmlUrl: String,
    @SerializedName("description")
    val description: String,
    @SerializedName("fork")
    val isForked: Boolean,
    @SerializedName("created_at")
    val createdAt: String?,
    @SerializedName("updated_at")
    val updatedAt: String?,
    @SerializedName("pushed_at")
    val pushedAt: String?,
    @SerializedName("homepage")
    val homepage: String,
    @SerializedName("size")
    val size: Int,
    @SerializedName("stargazers_count")
    val stargazersCount: Int,
    @SerializedName("watchers_count")
    val watchersCount: Int,
    @SerializedName("language")
    val language: String,
    @SerializedName("forks_count")
    val forksCount: Int,
    @SerializedName("open_issues_count")
    val openIssuesCount: Int,
    @SerializedName("score")
    val score: Double
): Parcelable {
    @IgnoredOnParcel
    var stargazersCountFormatted: String? = null
    @IgnoredOnParcel
    var watchersCountFormatted: String? = null
    @IgnoredOnParcel
    var forksCountFormatted: String? = null
    @IgnoredOnParcel
    var openIssuesCountFormatted: String? = null

    @IgnoredOnParcel
    var createdAtFormatted: String? = null
    @IgnoredOnParcel
    var updatedAtFormatted: String? = null
    @IgnoredOnParcel
    var pushedAtFormatted: String? = null
}

fun Repository.fillRest(): Repository {

    stargazersCountFormatted = stargazersCount.toHumanReadableFormat()
    watchersCountFormatted = watchersCount.toHumanReadableFormat()
    forksCountFormatted = forksCount.toHumanReadableFormat()
    openIssuesCountFormatted = openIssuesCount.toHumanReadableFormat()

    createdAtFormatted = createdAt?.toEpochMillis()?.toRelativeTimeFormat()
    updatedAtFormatted = updatedAt?.toEpochMillis()?.toRelativeTimeFormat()
    pushedAtFormatted = pushedAt?.toEpochMillis()?.toRelativeTimeFormat()

    return this
}