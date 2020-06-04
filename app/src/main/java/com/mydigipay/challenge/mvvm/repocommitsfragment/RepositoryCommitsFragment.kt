package com.mydigipay.challenge.mvvm.repocommitsfragment

import android.os.Bundle
import android.view.View
import androidx.databinding.library.baseAdapters.BR
import androidx.lifecycle.Observer
import com.mydigipay.challenge.R
import com.mydigipay.challenge.databinding.FragmentRepositoryCommitsBinding
import com.mydigipay.challenge.mvvm.base.BaseFragment
import com.mydigipay.challenge.mvvm.repocommitsfragment.adapter.CommitAdapter
import com.mydigipay.challenge.mvvm.repositorysearchfragment.RepositorySearchFragmentDirections
import javax.inject.Inject

/**
 * Created by Mahdi Zare Tahghigh Doost on 6/4/2020.
 *
 * mahdiZTD@gmail.com
 */
class RepositoryCommitsFragment :
    BaseFragment<FragmentRepositoryCommitsBinding, RepositoryCommitsViewModel>(),
    RepositoryCommitsNavigator {

    @Inject
    lateinit var repositoryCommitsViewModel: RepositoryCommitsViewModel

    @Inject
    lateinit var adapter: CommitAdapter

    override val bindingVariable: Int
        get() = BR.vm
    override val layoutId: Int
        get() = R.layout.fragment_repository_commits
    override val mViewModel: RepositoryCommitsViewModel
        get() = repositoryCommitsViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mViewModel.setNavigator(this)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initialRecyclerView()
        subscribeToLiveData()

        mViewModel.getRepoCommits(
            RepositoryCommitsFragmentArgs.fromBundle(requireArguments()).login,
            RepositoryCommitsFragmentArgs.fromBundle(requireArguments()).name
        )
    }

    private fun initialRecyclerView() {
        viewDataBinding.rvRepository.adapter = adapter
    }


    private fun subscribeToLiveData() {
        mViewModel.repoCommitsLiveData.observe(viewLifecycleOwner, Observer {
            if (it != null) {
                mViewModel.repositoryCommits.clear()
                mViewModel.repositoryCommits.addAll(it)
            }
        })
    }

}