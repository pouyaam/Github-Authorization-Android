package com.mydigipay.challenge.ui.repos

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import com.mydigipay.challenge.R

class RepositoriesListFragment : Fragment() {

    companion object {
        fun newInstance() = RepositoriesListFragment()
    }

    private lateinit var viewModel: RepositoriesListViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_repositories_list, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(RepositoriesListViewModel::class.java)
        // TODO: Use the ViewModel
    }

}
