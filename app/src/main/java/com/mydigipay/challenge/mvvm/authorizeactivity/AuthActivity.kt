package com.mydigipay.challenge.mvvm.authorizeactivity

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.mydigipay.challenge.R
import com.mydigipay.challenge.databinding.ActivityAuthBinding
import com.mydigipay.challenge.infrastructure.utils.Constants.CLIENT_ID
import com.mydigipay.challenge.infrastructure.utils.Constants.CLIENT_REDIRECT_URI

class AuthActivity : AppCompatActivity() {

    private lateinit var viewDataBinding: ActivityAuthBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        performDataBinding()
    }


    private fun performDataBinding() {
        viewDataBinding = DataBindingUtil.setContentView(this, R.layout.activity_auth)
        viewDataBinding.btnAuth.setOnClickListener {
            val url = "https://github.com/login/oauth/authorize?client_id=$CLIENT_ID&redirect_uri=$CLIENT_REDIRECT_URI&scope=repo user&state=0"
            val i = Intent(Intent.ACTION_VIEW)
            i.data = Uri.parse(url)
            startActivity(i)
        }
    }

}
