package com.mydigipay.challenge.presentation.view.ui

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import androidx.activity.viewModels
import androidx.appcompat.widget.Toolbar
import androidx.databinding.DataBindingUtil
import com.mydigipay.challenge.R
import com.mydigipay.challenge.databinding.ActivityAccessTokenBinding
import com.mydigipay.challenge.presentation.design.MviActivity
import com.mydigipay.challenge.presentation.viewmodel.AccessTokenViewModel
import com.mydigipay.challenge.presentation.viewstate.AccessTokenViewEffect
import com.mydigipay.challenge.presentation.viewstate.AccessTokenViewEvent
import com.mydigipay.challenge.presentation.viewstate.AccessTokenViewState

class AccessTokenActivity :
    MviActivity<AccessTokenViewState, AccessTokenViewEffect, AccessTokenViewEvent, AccessTokenViewModel>() {

    /**
     * Values
     */

    override val viewModel: AccessTokenViewModel by viewModels()

    private lateinit var dataBinding: ActivityAccessTokenBinding

    override fun onNewIntent(intent: Intent) {
        super.onNewIntent(intent)

        extractIntentParams(intent)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)

        return true
    }

    override fun initializeActivity(savedInstanceState: Bundle?) {
        // Nothing
    }

    override fun extractIntentParams(data: Intent?) {
        // Nothing
    }

    override fun setupViews() {
        // Set Content View
        dataBinding = DataBindingUtil.setContentView(this, R.layout.activity_access_token)
    }

    override fun setupActionBar() {
        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)
        val actionBar = supportActionBar
        actionBar!!.setDisplayShowTitleEnabled(true)
        actionBar.setDisplayHomeAsUpEnabled(false)
    }

    override fun setupNavigation() {
        /* Nothing */
    }

    override fun renderViewState(viewState: AccessTokenViewState) {
        TODO("Not yet implemented")
    }

    override fun renderViewEffect(viewEffect: AccessTokenViewEffect) {
        TODO("Not yet implemented")
    }
}
