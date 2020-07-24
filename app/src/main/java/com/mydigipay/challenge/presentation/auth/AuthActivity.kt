package com.mydigipay.challenge.presentation.auth

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View.GONE
import android.view.View.VISIBLE
import androidx.appcompat.app.AppCompatActivity
import com.mydigipay.challenge.app.component
import com.mydigipay.challenge.presentation.github.MainActivity
import com.mydigipay.challenge.presentation.github.R
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import kotlinx.android.synthetic.main.activity_auth.*
import javax.inject.Inject

const val CLIENT_ID = "685e6244dd56a72db4c6"
const val CLIENT_SECRET = "50c7fa47bd384aaf6487c4ae2a375a8f6891cda0"
const val REDIRECT_URI = "challenge://mydigipay.com/mohsen/callback"
const val STATE = "0123456"

class AuthActivity : AppCompatActivity() {

    private lateinit var compositeDisposable: CompositeDisposable

    @Inject
    lateinit var viewModel: AuthActivityViewModel
    private val keyCode = "code"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        component.viewModelProviderFactory.create().inject(this)

        compositeDisposable = CompositeDisposable()

        if (viewModel.isUserAuthorized()) {
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        } else {
            setContentView(R.layout.activity_auth)
            authorize_btn.setOnClickListener {
                val url =
                    "https://github.com/login/oauth/authorize?client_id=$CLIENT_ID&redirect_uri=$REDIRECT_URI&scope=repo user&state=$STATE"
                val i = Intent(Intent.ACTION_VIEW)
                i.data = Uri.parse(url)
                startActivity(i)
            }
        }


    }

    override fun onNewIntent(intent: Intent?) {
        super.onNewIntent(intent)
        intent?.let {
            if (Intent.ACTION_VIEW == it.action) {
                val code = it.data?.getQueryParameter(keyCode)
                code?.let {
                    viewModel.fetchAccessToken(code)
                }
                viewModel.getState().observeOn(AndroidSchedulers.mainThread())
                    .subscribe {
                        handleViewState(it)
                    }.let {
                        compositeDisposable.add(it)
                    }
            }
        }
    }

    private fun handleViewState(state: AuthActivityState) {
        when (state) {
            is AuthActivityState.Loading -> {
                network_error_group.visibility = GONE
                authorize_btn.visibility = GONE
                loading.show()

            }
            is AuthActivityState.SuccessfullyGotToken -> {
                startActivity(Intent(this, MainActivity::class.java))
                finish()
            }
            is AuthActivityState.Error -> {
                network_error_group.visibility = VISIBLE
                authorize_btn.visibility = GONE
                loading.hide()
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        compositeDisposable.dispose()
    }
}
