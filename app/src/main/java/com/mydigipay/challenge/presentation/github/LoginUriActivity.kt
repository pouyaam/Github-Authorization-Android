package com.mydigipay.challenge.presentation.github

import android.app.Activity
import android.os.Bundle

class LoginUriActivity : Activity() {
//    private val tokenRepository: TokenRepository by inject()
//    private val accessTokenDataSource: AccessTokenDataSource by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.login_uri_activity)
    }

    override fun onResume() {
        super.onResume()

//        val intent = intent
//        if (Intent.ACTION_VIEW == intent.action) {
//            val uri = intent.data
//            val code = uri?.getQueryParameter("code") ?: ""
//            code.takeIf { it.isNotEmpty() }?.let { code ->
//                val accessTokenJob = CoroutineScope(Dispatchers.IO).launch {
//                    val response = accessTokenDataSource.accessToken(
//                        RequestAccessToken(
//                            CLIENT_ID,
//                            CLIENT_SECRET,
//                            code,
//                            REDIRECT_URI,
//                            "0"
//                        )
//                    ).await()
//
//                    tokenRepository.saveToken(response.accessToken).await()
//                }
//
//                accessTokenJob.invokeOnCompletion {
//                    CoroutineScope(Dispatchers.Main).launch {
//                        token.text = tokenRepository.readToken().await()
//                        this.cancel()
//                        accessTokenJob.cancelAndJoin()
//                    }
//                }
//            } ?: run { finish() }
//        }


    }
}