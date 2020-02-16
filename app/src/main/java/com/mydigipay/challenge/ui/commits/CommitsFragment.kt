package com.mydigipay.challenge.ui.commits

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.mydigipay.challenge.github.R
import kotlinx.android.synthetic.main.fragment_commits.*
import kotlinx.android.synthetic.main.fragment_home.*
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
                adapter.items = it.toMutableList()
                Log.i("GitToken", it.toString())
            }
        })
        recyclerCommits.apply {
            layoutManager = LinearLayoutManager(this@CommitsFragment.context!!)
            adapter = this@CommitsFragment.adapter
        }

        viewModel.requestCommits()
    }

}