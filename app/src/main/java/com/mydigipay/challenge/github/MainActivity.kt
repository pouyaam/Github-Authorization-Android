package com.mydigipay.challenge.github

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*

const val CLIENT_ID = "31d8fee088f5e2caed71"
const val CLIENT_SECRET = "ba1e3bc0669edb06c928d9233d7ce775d87a787c"
const val REDIRECT_URI = "challenge://mydigipay.com/login/callback"

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        authorize.setOnClickListener { view ->
            val url = "https://github.com/login/oauth/authorize?client_id=$CLIENT_ID&redirect_uri=$REDIRECT_URI&scope=repo user&state=0"
            val i = Intent(Intent.ACTION_VIEW)
            i.data = Uri.parse(url)
            startActivity(i)
        }
    }
}
