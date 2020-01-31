package com.mydigipay.challenge.presentation.ui.github

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.ProgressBar
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.observe
import com.github.mohammadsianaki.core.extenstion.hide
import com.github.mohammadsianaki.core.extenstion.show
import com.github.mohammadsianaki.core.model.Resource
import com.github.mohammadsianaki.core.model.ResourcesState
import com.mydigipay.challenge.github.R
import com.mydigipay.challenge.presentation.model.AccessTokenModel
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.getViewModel

class GithubActivity : AppCompatActivity() {

    private lateinit var viewModel: GithubViewModel
    private lateinit var loading: ProgressBar
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = getViewModel()
        handleIncomingIntent()
        setContentView(R.layout.activity_github)
        initUi()
    }

    private fun initUi() {
        loading = findViewById(R.id.loading)
    }

    override fun onResume() {
        super.onResume()
        viewModel.getLiveData().observe(this) { resource -> handleData(resource) }
    }

    private fun handleData(resource: Resource<AccessTokenModel>?) {
        resource?.let {
            when (resource.resourcesState) {
                ResourcesState.Loading -> {
                    loading.show()
                }
                ResourcesState.Success -> {
                    loading.hide()
                }
            }
        }
    }

    private fun handleIncomingIntent() {
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
    }

    companion object {
        const val KEY_CODE = "code"
    }
}
