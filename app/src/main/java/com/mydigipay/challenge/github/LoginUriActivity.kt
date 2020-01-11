package com.mydigipay.challenge.github

import android.app.Activity
import android.os.Bundle
import com.mydigipay.challenge.repository.token.TokenRepository
import org.koin.android.ext.android.inject
import android.content.Intent
import kotlinx.android.synthetic.main.login_uri_activity.*
import kotlinx.coroutines.*

class LoginUriActivity : Activity() {
    private val tokenRepository: TokenRepository by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.login_uri_activity)

        val intent = intent
        if (Intent.ACTION_VIEW == intent.action) {
            val uri = intent.data
            val token = uri!!.getQueryParameter("code") ?: ""
            CoroutineScope(Dispatchers.IO).launch {
                tokenRepository.saveToken(token).await()
                this.cancel()
            }
        }
    }

    override fun onResume() {
        super.onResume()

        CoroutineScope(Dispatchers.Main).launch {
            token.text = tokenRepository.readToken().await()
            this.cancel()
        }


    }
}