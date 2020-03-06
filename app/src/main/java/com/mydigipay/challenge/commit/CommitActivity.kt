package com.mydigipay.challenge.commit

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.mydigipay.challenge.commit.repository.CommitAdapter
import com.mydigipay.challenge.commit.repository.model.CommitResponseModel
import com.mydigipay.challenge.core.base.BaseActivity
import com.mydigipay.challenge.core.extension.toast
import com.mydigipay.challenge.github.R
import com.mydigipay.challenge.profile.ProfileActivity
import com.mydigipay.challenge.profile.USERNAME_PARAM
import com.mydigipay.challenge.profile.USER_TYPE_PARAM
import com.mydigipay.challenge.profile.UserType
import kotlinx.android.synthetic.main.activity_commit.*
import org.koin.android.viewmodel.ext.android.viewModel

const val REPO_PARAM = "repo"
const val OWNER_PARAM = "owner"

class CommitActivity : BaseActivity() {

    private val viewModel: CommitViewModel by viewModel()
    private lateinit var commitAdapter: CommitAdapter
    private val commits = mutableListOf<CommitResponseModel>()

    private var repo: String? = null
    private var owner: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_commit)

        initView()
        subscribeViews()

        // get parameter from intent
        repo = intent.getStringExtra(REPO_PARAM)
        owner = intent.getStringExtra(OWNER_PARAM)
    }

    override fun initView() {
        setupRecyclerView()
    }

    override fun subscribeViews() {
        viewModel.commits.observe(this, Observer {
            if (it.isNotEmpty()) {
                commits.addAll(it)
                commitAdapter.notifyDataSetChanged()
            } else {
                toast("Commits not found")
            }
        })

        viewModel.showProgressBar.observe(this, Observer {
            commitPB.visibility = View.VISIBLE
        })

        viewModel.hideProgressBar.observe(this, Observer {
            commitPB.visibility = View.INVISIBLE
        })

        viewModel.mError.observe(this, Observer {
            toast(it)
        })
    }

    private fun sendCommitsRequest(owner: String, repo: String) {
        viewModel.getCommits(owner, repo)
    }

    private fun setupRecyclerView() {
        commitRV.layoutManager = LinearLayoutManager(this)
        commitRV.setHasFixedSize(true)

        commitAdapter = CommitAdapter(commits, this) {
            val intent = Intent(this, ProfileActivity::class.java)
            intent.putExtra(USERNAME_PARAM, it.author?.login)
            intent.putExtra(USER_TYPE_PARAM, UserType.OtherUser.name)
            startActivity(intent)
        }
        commitRV.adapter = commitAdapter
    }

    override fun networkAvailable() {
        repo?.let { repo ->
            owner?.let { owner ->
                sendCommitsRequest(owner, repo)
                commitAppBarTitleTV.text = repo
            } ?: run {
                toast("Owner is null!")
            }
        } ?: run {
            toast("Repo is null!")
        }
    }
}
