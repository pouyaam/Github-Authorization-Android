package com.mydigipay.challenge.ui.home

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.miguelcatalan.materialsearchview.MaterialSearchView
import com.mydigipay.challenge.extentions.githubApiDialog
import com.mydigipay.challenge.github.R
import com.mydigipay.challenge.utils.*
import kotlinx.android.synthetic.main.fragment_home.*
import org.koin.android.viewmodel.ext.android.viewModel

class HomeFragment : Fragment() {

    private val viewModel by viewModel<HomeViewModel>()

    private val adapter by lazy {
        RepositoryListAdapter(context!!, onRepositoryClicked = {
            findNavController().navigate(
                HomeFragmentDirections.actionHomeToCommits(
                    it.owner.login,
                    it.name
                )
            )
        })
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        Log.i("GitToken", token.toString())
        HomeFragmentArgs.fromBundle(arguments!!).code?.let {
            githubCode = it
        }

        viewModel.homeState.observe(viewLifecycleOwner, Observer { state ->
            repositoriesContainer.isVisible = state.requiredCode.not()
            authorize.isVisible = state.requiredCode
            state.repositories.takeIf { it.isNotEmpty() }?.let {
                adapter.originalData = it.toMutableList()
                Log.i("repositories", it.toString())
            }
        })
        recyclerRepositories.apply {
            layoutManager = LinearLayoutManager(this@HomeFragment.context!!)
            adapter = this@HomeFragment.adapter
        }


        authorize.setOnClickListener {
            startActivity(
                Intent(Intent.ACTION_VIEW).apply {
                    data = Uri.parse(GITHUB_CODE_URL.format(githubClinetId))
                }
            )
        }
        fabProfile.setOnClickListener {
            findNavController().navigate(HomeFragmentDirections.actionHomeToProfile())
        }
        initSearch()


        if (githubClinetId == null || githubClinetSecret == null)
            activity!!.githubApiDialog { id, secret ->
                githubClinetId = id
                githubClinetSecret = secret
            }
    }

    private fun initSearch() {
        searchView.post { searchView.showSearch() }
        searchView.findViewById<View>(com.miguelcatalan.materialsearchview.R.id.action_up_btn)
            .isVisible = false
        searchView.setOnQueryTextListener(object : MaterialSearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                newText?.let { adapter.filter(it) }
                return true
            }

        })
    }

}