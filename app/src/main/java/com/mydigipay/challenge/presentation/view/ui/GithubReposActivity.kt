package com.mydigipay.challenge.presentation.view.ui

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import androidx.appcompat.widget.Toolbar
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.FragmentManager
import com.mydigipay.challenge.R
import com.mydigipay.challenge.common.BaseActivity
import com.mydigipay.challenge.databinding.ActivityGithubReposBinding
import javax.inject.Inject

class GithubReposActivity : BaseActivity() {

    /**
     * Values
     */

    private lateinit var dataBinding: ActivityGithubReposBinding

    @Inject
    lateinit var fragmentManager: FragmentManager

    @Inject
    lateinit var githubReposFragment: GithubReposFragment

    @Inject
    lateinit var repoCommitsFragment: RepoCommitsFragment

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
        // Nothing
    }

    override fun setupViews() {
        // Set Content View
        dataBinding = DataBindingUtil.setContentView(this, R.layout.activity_github_repos)
    }

    override fun setupActionBar() {
        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)
        val actionBar = supportActionBar
        actionBar!!.setDisplayShowTitleEnabled(true)
        actionBar.setDisplayHomeAsUpEnabled(false)
    }

    override fun setupNavigation() {
        /* It will be set automatically. */
    }

    override fun setupObservers() {
        super.setupObservers()

        // Nothing
    }

    /****************************************************
     * SERVICE BINDING
     ***************************************************/

    // Nothing

    companion object {
        const val EXTRA_FRAGMENT_ID = "EXTRA_FRAGMENT_ID"
        const val EXTRA_FRAGMENT_TITLE = "EXTRA_FRAGMENT_TITLE"
    }
}
