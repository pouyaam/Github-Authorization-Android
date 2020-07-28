package com.mydigipay.challenge.presentation.view.ui

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.Menu
import android.widget.Toast
import androidx.appcompat.widget.Toolbar
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.mydigipay.challenge.AUTHORIZE_URL
import com.mydigipay.challenge.REDIRECT_URI
import com.mydigipay.challenge.CLIENT_ID
import com.mydigipay.challenge.R
import com.mydigipay.challenge.databinding.ActivityAccessTokenBinding
import com.mydigipay.challenge.presentation.design.MviActivity
import com.mydigipay.challenge.presentation.viewmodel.AccessTokenViewModel
import com.mydigipay.challenge.presentation.viewstate.*
import com.mydigipay.challenge.presentation.viewstate.AccessTokenFetchStatus.Fetching
import javax.inject.Inject

class AccessTokenActivity :
    MviActivity<AccessTokenViewState, AccessTokenViewEffect, AccessTokenViewEvent, AccessTokenViewModel>() {

    /**
     * Values
     */

    @Inject
    internal lateinit var viewModelProviderFactory: ViewModelProvider.Factory

    private lateinit var dataBinding: ActivityAccessTokenBinding

    /**
     * Workflow
     */

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
        data?.let {
            viewModel.process(AccessTokenViewEvent.NewIntentReceived(it))
        }
    }

    override fun setupViews() {
        // Set Content View
        dataBinding = DataBindingUtil.setContentView(this, R.layout.activity_access_token)

        dataBinding.content.authorizeButton.setOnClickListener {
            viewModel.process(AccessTokenViewEvent.AuthorizationButtonClicked)
        }
    }

    override fun setupActionBar() {
        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)
        val actionBar = supportActionBar
        actionBar!!.setDisplayShowTitleEnabled(true)
        actionBar.setDisplayHomeAsUpEnabled(false)
    }

    override fun setupNavigation() {
        // Nothing
    }

    override fun setupObservers() {
        super.setupObservers()

        viewModel =
            ViewModelProvider(this, viewModelProviderFactory).get(AccessTokenViewModel::class.java)

        viewModel.viewStates().observe(this, viewStateObserver)
        viewModel.viewEffects().observe(this, viewEffectObserver)
    }

    override fun renderViewState(viewState: AccessTokenViewState) {
        with(dataBinding.content) {
            this.loading = viewState.fetchStatus == Fetching
            executePendingBindings()
        }
    }

    override fun renderViewEffect(viewEffect: AccessTokenViewEffect) {
        when (viewEffect) {
            is AccessTokenViewEffect.ShowToast -> showToast(viewEffect.message)
            AccessTokenViewEffect.StartAuthorizationAction -> startAuthorizationAction()
            AccessTokenViewEffect.NavigateToGithubRepos -> navigateToGithubRepos()
            else -> throw IllegalArgumentException("Un-expected view effect")
        }
    }

    /**
     * Functionality
     */

    private fun navigateToGithubRepos() {
        val i: Intent = Intent(this, GithubReposActivity::class.java)
        startActivity(i)
    }

    private fun startAuthorizationAction() {
        val url = "$AUTHORIZE_URL" +
                "?client_id=$CLIENT_ID" +
                "&redirect_uri=$REDIRECT_URI" +
                "&scope=repo,user" +
                "&state=0"

        val intent = Intent(Intent.ACTION_VIEW)
        intent.data = Uri.parse(url)
        startActivity(intent)
    }

    private fun showToast(message: String) {
        Toast.makeText(
            this,
            message,
            Toast.LENGTH_LONG
        ).show()
    }
}
