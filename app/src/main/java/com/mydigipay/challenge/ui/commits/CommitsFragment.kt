package com.mydigipay.challenge.ui.commits

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
import com.mydigipay.challenge.github.R
import kotlinx.android.synthetic.main.fragment_commits.*
import org.koin.android.viewmodel.ext.android.viewModel

class CommitsFragment : Fragment() {


    private val viewModel by viewModel<CommitsViewModel>()

    private val adapter by lazy {
        CommitListAdapter(context!!, onCommitClicked = {

        })
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_commits, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        viewModel.owner = CommitsFragmentArgs.fromBundle(arguments!!).owner
        viewModel.repo = CommitsFragmentArgs.fromBundle(arguments!!).repo

        viewModel.commitState.observe(viewLifecycleOwner, Observer { state ->
            state.commits.takeIf { it.isNotEmpty() }?.let {
                adapter.originalData = it.toMutableList()
                Log.i("GitToken", it.toString())
            }
        })
        recyclerCommits.apply {
            layoutManager = LinearLayoutManager(this@CommitsFragment.context!!)
            adapter = this@CommitsFragment.adapter
        }

        initSearch()

        viewModel.requestCommits()
    }

    private fun initSearch() {
        searchView.post { searchView.showSearch() }
        searchView.findViewById<View>(com.miguelcatalan.materialsearchview.R.id.action_up_btn)
            .setOnClickListener {
                findNavController().navigateUp()
            }
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