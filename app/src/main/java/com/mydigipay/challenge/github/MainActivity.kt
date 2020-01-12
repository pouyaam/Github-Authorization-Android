package com.mydigipay.challenge.github

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*

const val CLIENT_ID = "CLIENT_ID"
const val CLIENT_SECRET = "CLIENT_SECRET"
const val REDIRECT_URI = "REDIRECT_URI"

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
