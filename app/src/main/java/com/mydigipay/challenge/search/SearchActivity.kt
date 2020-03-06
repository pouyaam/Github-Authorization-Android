package com.mydigipay.challenge.search

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.view.inputmethod.EditorInfo
import android.widget.SearchView
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.mydigipay.challenge.commit.CommitActivity
import com.mydigipay.challenge.commit.OWNER_PARAM
import com.mydigipay.challenge.commit.REPO_PARAM
import com.mydigipay.challenge.core.base.BaseActivity
import com.mydigipay.challenge.core.extension.hideKeyboard
import com.mydigipay.challenge.core.extension.toast
import com.mydigipay.challenge.github.R
import com.mydigipay.challenge.profile.ProfileActivity
import com.mydigipay.challenge.profile.USERNAME_PARAM
import com.mydigipay.challenge.profile.USER_TYPE_PARAM
import com.mydigipay.challenge.profile.UserType
import com.mydigipay.challenge.search.repository.model.RepositoryModel
import kotlinx.android.synthetic.main.activity_search.*
import org.koin.android.viewmodel.ext.android.viewModel

class SearchActivity : BaseActivity() {

    private val viewModel: SearchViewModel by viewModel()
    private lateinit var repositoryAdapter: RepositoryAdapter
    private val repositories = mutableListOf<RepositoryModel>()

    private var searchKey: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search)

        initView()
        subscribeViews()
    }

    override fun initView() {
        setupRecyclerView()

        profileButton.setOnClickListener {
            val intent = Intent(this, ProfileActivity::class.java)
            intent.putExtra(USER_TYPE_PARAM, UserType.AuthenticatedUser.name)
            startActivity(intent)
        }

        searchET?.setOnEditorActionListener { v, actionId, event ->
            if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                searchKey = searchET?.text.toString()

                searchKey?.let {
                    sendSearchRequest(it)
                    hideKeyboard()
                } ?: run {
                    toast("Search key is empty!")
                }

                true
            } else {
                false
            }
        }
    }

    override fun subscribeViews() {
        viewModel.repositories.observe(this, Observer {
            if (it.isNotEmpty()) {
                repositories.clear()
                repositories.addAll(it)
                repositoryAdapter.notifyDataSetChanged()
                repositoryRV.scrollToPosition(0)
            } else {
                toast("Repository not found")
            }
        })

        viewModel.showProgressBar.observe(this, Observer {
            searchPB.visibility = View.VISIBLE
        })

        viewModel.hideProgressBar.observe(this, Observer {
            searchPB.visibility = View.INVISIBLE
        })

        viewModel.mError.observe(this, Observer {
            toast(it)
        })
    }

    private fun sendSearchRequest(searchKey: String) {
        viewModel.getRepositories(searchKey)
    }

    private fun setupRecyclerView() {
        repositoryRV.layoutManager = LinearLayoutManager(this)
        repositoryRV.setHasFixedSize(true)

        repositoryAdapter = RepositoryAdapter(repositories, this) {
            val intent = Intent(this, CommitActivity::class.java)
            intent.putExtra(REPO_PARAM, it.name)
            intent.putExtra(OWNER_PARAM, it.owner.login)
            startActivity(intent)
        }
        repositoryRV.adapter = repositoryAdapter
    }

    override fun networkAvailable() {
        searchKey?.let {
            sendSearchRequest(it)
            hideKeyboard()
        }
    }
}
