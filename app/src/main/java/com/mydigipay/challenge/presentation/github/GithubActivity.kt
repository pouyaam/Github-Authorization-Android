package com.mydigipay.challenge.presentation.github

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.lifecycleScope
import com.mydigipay.challenge.app.component
import kotlinx.coroutines.handleCoroutineException
import kotlinx.coroutines.launch
import javax.inject.Inject

class GithubActivity : Activity() {
    private val KEY_CODE = "code"

    @Inject
    lateinit var viewModel: GithubActivityViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.login_uri_activity)
        component.viewModelProviderFactory.create().inject(this)
        handleIncomingIntent()
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