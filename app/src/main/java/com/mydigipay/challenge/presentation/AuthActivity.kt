package com.mydigipay.challenge.presentation

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.mydigipay.challenge.app.component
import com.mydigipay.challenge.presentation.github.GithubActivity
import com.mydigipay.challenge.presentation.github.R
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject

const val CLIENT_ID = "Iv1.791f3bf9dee10749"
const val CLIENT_SECRET = "b252036c3238ec98a4a1dbd2ad6683c5664295a7"
const val REDIRECT_URI = ""
const val STATE = "0"

class MainActivity : AppCompatActivity() {

    @Inject
    lateinit var viewModel: AuthActivityViewModel
    private val KEY_CODE = "code"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        component.viewModelProviderFactory.create().inject(this)
        if (viewModel.isUserAuthorized()) {
            startActivity(Intent(this, GithubActivity::class.java))
            finish()
        } else {
            setContentView(R.layout.activity_main)
            handleIncomingIntent()
            authorize.setOnClickListener {
                val url =
                    "https://github.com/login/oauth/authorize?client_id=$CLIENT_ID&redirect_uri=$REDIRECT_URI&scope=repo user&state=$STATE"
                val i = Intent(Intent.ACTION_VIEW)
                i.data = Uri.parse(url)
                startActivity(i)
            }
        }


    }

    private fun handleIncomingIntent() {
        if (Intent.ACTION_VIEW == intent.action) {
            val code = intent.data?.getQueryParameter(KEY_CODE)
            code?.let {
                viewModel.fetchAccessToken(code)
            }
        }
    }
}
