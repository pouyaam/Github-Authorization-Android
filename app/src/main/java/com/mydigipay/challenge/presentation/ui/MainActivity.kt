package com.mydigipay.challenge.presentation.ui

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.mydigipay.challenge.github.R
import com.mydigipay.challenge.presentation.ui.github.GithubActivity
import kotlinx.android.synthetic.main.activity_main.*
import org.koin.androidx.viewmodel.ext.android.getViewModel

const val CLIENT_ID = "Iv1.551bf597c857ddf9"
const val CLIENT_SECRET = "0ca09241dd2b5b9d6aa4d7d8affe1f617f45fc91"
const val REDIRECT_URI = ""

class MainActivity : AppCompatActivity() {
    private lateinit var viewModel: MainViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = getViewModel()
        viewModel.readToekn().takeIf { it.isNotEmpty() }?.let { token ->
            startActivity(Intent(this, GithubActivity::class.java))
            finish()
        } ?: run {
            setContentView(R.layout.activity_main)
            authorize.setOnClickListener { view ->
                startActivity(Intent(Intent.ACTION_VIEW).apply {
                    data = Uri.parse(
                        "https://github.com/login/oauth/authorize?client_id=$CLIENT_ID&redirect_uri=$REDIRECT_URI&scope=repo user&state=0"
                    )
                })
            }
        }
    }
}
