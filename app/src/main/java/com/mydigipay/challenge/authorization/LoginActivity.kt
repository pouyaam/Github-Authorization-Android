package com.mydigipay.challenge.authorization

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.Observer
import com.mydigipay.challenge.authorization.repository.oauth.model.RequestAccessToken
import com.mydigipay.challenge.core.Const
import com.mydigipay.challenge.core.base.BaseActivity
import com.mydigipay.challenge.core.extension.toast
import com.mydigipay.challenge.github.R
import com.mydigipay.challenge.search.SearchActivity
import kotlinx.android.synthetic.main.login_activity.*
import org.koin.android.viewmodel.ext.android.viewModel

class LoginActivity : BaseActivity() {
    companion object {
        private const val LOGIN_BASE_URI = "https://github.com/login/oauth/authorize"
    }

    private val viewModel: AuthorizationViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.login_activity)

        initView()
        subscribeViews()
    }

    override fun onResume() {
        super.onResume()

        if (Intent.ACTION_VIEW == intent.action) {
            val uri = intent.data
            val code = uri?.getQueryParameter("code") ?: ""

            code.takeIf { it.isNotEmpty() }?.let { code ->
                sendAccessTokenRequest(
                    RequestAccessToken(
                        Const.Github.CLIENT_ID,
                        Const.Github.CLIENT_SECRET,
                        code,
                        Const.Github.REDIRECT_URI,
                        "0"
                    )
                )
            } ?: run { finish() }
        }
    }

    override fun initView() {
        authorizationButton.setOnClickListener {
            sendLoginRequest()
        }
    }

    override fun subscribeViews() {
        viewModel.loginUri.observe(this, Observer {
            val loginIntent = Intent(Intent.ACTION_VIEW)
            loginIntent.data = Uri.parse(it)
            startActivity(loginIntent)
        })

        viewModel.accessToken.observe(this, Observer {
            it?.let { accessToken ->
                viewModel.saveAccessToken(accessToken)
                Log.i("access token", accessToken)
            } ?: run {
                toast("Access token is null!")
            }
        })

        viewModel.saveAccessTokenSuccess.observe(this, Observer {
            startActivity(Intent(this, SearchActivity::class.java))
            finish()
        })

        viewModel.mError.observe(this, Observer {
            toast(it)
        })
    }

    private fun sendLoginRequest() {
        viewModel.handleLoginUri(LOGIN_BASE_URI, Const.Github.CLIENT_ID, Const.Github.REDIRECT_URI)
    }

    private fun sendAccessTokenRequest(accessTokenRequest: RequestAccessToken) {
        viewModel.getAccessToken(accessTokenRequest)
    }

    override fun networkAvailable() {
    }
}