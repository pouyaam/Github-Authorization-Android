package com.mydigipay.challenge.presentation

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.observe
import com.mydigipay.challenge.github.R
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
                }
            } ?: run { finish() }
        }
        viewModel.getLiveData().observe(this) {

        }
    }

    companion object {
        const val KEY_CODE = "code"
    }
}