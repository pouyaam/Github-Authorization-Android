package com.mydigipay.challenge.dataaccess.model


import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import com.mydigipay.challenge.util.toHumanReadable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class UserResponse(
    @SerializedName("login")
    val login: String = "",
    @SerializedName("id")
    val id: Int = 0,
    @SerializedName("node_id")
    val nodeId: String = "",
    @SerializedName("avatar_url")
    val avatarUrl: String = "",
    @SerializedName("name")
    val name: String = "",
    @SerializedName("company")
    val company: String? = "",
    @SerializedName("blog")
    val blog: String = "",
    @SerializedName("location")
    val location: String = "",
    @SerializedName("email")
    val email: String? = "",
    @SerializedName("hireable")
    val hireable: Boolean = false,
    @SerializedName("bio")
    val bio: String? = "",
    @SerializedName("public_repos")
    val publicRepos: Int = 0,
    @SerializedName("public_gists")
    val publicGists: Int = 0,
    @SerializedName("followers")
    val followers: Int = 0,
    @SerializedName("following")
    val following: Int = 0,
    @SerializedName("created_at")
    val createdAt: String = "",
    @SerializedName("updated_at")
    val updatedAt: String = "",
    @SerializedName("private_gists")
    val privateGists: Int = 0,
    @SerializedName("total_private_repos")
    val totalPrivateRepos: Int = 0,
    @SerializedName("owned_private_repos")
    val ownedPrivateRepos: Int = 0,
    @SerializedName("disk_usage")
    val diskUsage: Int = 0,
    @SerializedName("collaborators")
    val collaborators: Int = 0,
    @SerializedName("two_factor_authentication")
    val twoFactorAuthentication: Boolean = false
) : Parcelable {
    fun getEmailAddress() = if (!email.isNullOrBlank()) "Email: $email" else "Email is not set"
    fun getCompanyName() =
        if (!company.isNullOrBlank()) "Company: $company" else "Company is not set"

    fun getUserBio() = if (!bio.isNullOrBlank()) bio else "bio is empty"
    fun getFollowersCount() = "${followers.toHumanReadable()} followers"
    fun getFollowingCount() = "${following.toHumanReadable()} following"
}