package com.mydigipay.challenge.presentation

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.observe
import com.github.mohammadsianaki.core.model.ErrorHolder
import com.github.mohammadsianaki.core.model.ResourcesState
import com.mydigipay.challenge.github.R
import com.mydigipay.challenge.github.databinding.LoginUriActivityBinding
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.getViewModel


class LoginUriActivity : AppCompatActivity() {
    private lateinit var viewModel: LoginUriViewModel
    private lateinit var binding: LoginUriActivityBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.login_uri_activity)
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
            } ?: run {
                Log.d("<<<", "null herer")
            }
        }
        viewModel.getLiveData().observe(this) { resource ->
            when (resource.resourcesState) {
                ResourcesState.Success -> {
                    handleData(resource.data)
                }
                ResourcesState.Loading -> {
                    handleLoading()
                }
                ResourcesState.Failure -> {
                    handleFailure(resource.failure)
                }
            }
        }
    }

    private fun handleFailure(failure: ErrorHolder?) {
        // todo : handle this situation
    }

    private fun handleLoading() {
        // todo : handle this situation
    }

    private fun handleData(data: AccessTokenModel?) {
        data?.let {
            binding.itemData = it
            Log.d("<<<activity", "$it")
        }
    }

    companion object {
        const val KEY_CODE = "code"
    }
}