package com.mydigipay.challenge.profile

import android.os.Bundle
import androidx.lifecycle.Observer
import com.bumptech.glide.Glide
import com.mydigipay.challenge.authorization.repository.token.TokenRepository
import com.mydigipay.challenge.core.base.BaseActivity
import com.mydigipay.challenge.core.extension.toast
import com.mydigipay.challenge.github.R
import kotlinx.android.synthetic.main.activity_profile.*
import org.koin.android.ext.android.inject
import org.koin.android.viewmodel.ext.android.viewModel

const val USERNAME_PARAM = "username"
const val USER_TYPE_PARAM = "userType"

class ProfileActivity : BaseActivity() {
    private val viewModel: ProfileViewModel by viewModel()
    private val tokenRepository: TokenRepository by inject()

    private var username: String? = null
    private var userType: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)

        initView()
        subscribeViews()

        // get parameter
        username = intent.getStringExtra(USERNAME_PARAM)
        userType = intent.getStringExtra(USER_TYPE_PARAM)
    }

    override fun initView() {
    }

    override fun subscribeViews() {
        viewModel.userInfo.observe(this, Observer {
            profileUsernameTV.text = it.login
            profileNameTV.text = it.name
            profileBioTV.text = it.bio
            profileCompanyTV.text = it.company
            profileEmailTV.text = it.email
            profileWebsiteTV.text = it.organizationUrl
            profileFollowersTV.text = it.followers.toString()
            profileFollowingTV.text = it.following.toString()
            profileRepositoriesTV.text = it.repositories.toString()

            Glide.with(this)
                .load(it.avatarUrl)
                .into(profileBannerAvatarIV)

            Glide.with(this)
                .load(it.avatarUrl)
                .circleCrop()
                .into(profileAvatarIV)
        })

        viewModel.mError.observe(this, Observer {
            toast(it)
        })
    }

    private fun sendRequest(username: String?, userType: String) {
        viewModel.getUserInfo(username, userType, "bearer ${tokenRepository.readToken()}")
    }

    override fun networkAvailable() {
        userType?.let {
            sendRequest(username, it)
        } ?: run {
            toast("user type is null")
        }
    }
}
