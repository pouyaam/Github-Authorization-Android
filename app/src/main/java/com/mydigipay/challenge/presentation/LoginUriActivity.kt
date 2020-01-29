package com.mydigipay.challenge.presentation

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.mydigipay.challenge.github.R
import kotlinx.android.synthetic.main.login_uri_activity.*
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.getViewModel


class LoginUriActivity : AppCompatActivity() {
    private lateinit var viewModel: LoginUriViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.login_uri_activity)
    }

    override fun onResume() {
        super.onResume()
        viewModel = getViewModel()
        if (Intent.ACTION_VIEW == intent.action) {
            val code = intent.data?.getQueryParameter(KEY_CODE) ?: ""
            code.takeIf { code.isNotEmpty() }?.let { code ->
                lifecycleScope.launch {
                    viewModel.fetchAccessToken(code)
                    token.text = viewModel.getToken()
                }
            } ?: run { finish() }
        }
    }

    companion object {
        const val KEY_CODE = "code"
    }
}