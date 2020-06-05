package com.mydigipay.challenge.mvvm.repositorysearchfragment

import android.os.Bundle
import android.view.View
import androidx.appcompat.widget.SearchView
import androidx.databinding.library.baseAdapters.BR
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.mydigipay.challenge.R
import com.mydigipay.challenge.databinding.FragmentRepositorySearchBinding
import com.mydigipay.challenge.mvvm.base.BaseActivity
import com.mydigipay.challenge.mvvm.base.BaseFragment
import com.mydigipay.challenge.mvvm.repositorysearchfragment.adapter.RepositoryAdapter
import javax.inject.Inject

/**
 * Created by Mahdi Zare Tahghigh Doost on 6/4/2020.
 *
 * mahdiZTD@gmail.com
 */
class RepositorySearchFragment :
    BaseFragment<FragmentRepositorySearchBinding, RepositorySearchViewModel>(),
    RepositorySearchNavigator, RepositoryAdapter.OnItemClickListener {

    @Inject
    lateinit var repositorySearchViewModel: RepositorySearchViewModel

    @Inject
    lateinit var adapter: RepositoryAdapter

    override val bindingVariable: Int
        get() = BR.vm
    override val layoutId: Int
        get() = R.layout.fragment_repository_search
    override val mViewModel: RepositorySearchViewModel
        get() = repositorySearchViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mViewModel.setNavigator(this)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initialRecyclerView()
        initialSearchView()
        subscribeToLiveData()

    }

    private fun initialRecyclerView() {
        adapter.clickListener = this
        viewDataBinding.rvRepository.adapter = adapter
    }

    private fun initialSearchView() {
        viewDataBinding.searchViewQuery.setOnQueryTextListener(object :
            SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                if (query != null) {
                    (requireActivity() as BaseActivity<*, *>).hideKeyboard()
                    mViewModel.searchInRepositories(query)
                }
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                return true
            }

        })
    }

    private fun subscribeToLiveData() {
        mViewModel.repoLiveData.observe(viewLifecycleOwner, Observer {
            if (it != null) {
                mViewModel.repositories.clear()
                mViewModel.repositories.addAll(it)
            }
        })
    }

    override fun onRepoCommitsClicked(login: String?, name: String?) {
        findNavController().navigate(
            RepositorySearchFragmentDirections.actionRepositorySearchFragmentToRepositoryCommitFragment(
                login ?: "",
                name ?: ""
            )
        )
    }

    override fun onRepoUserProfileClicked(username: String?) {
        findNavController().navigate(
            RepositorySearchFragmentDirections.actionRepositorySearchFragmentToProfileFragment(
                username ?: ""
            )
        )
    }
    override fun showError(message: String?) {
        showSnackMessageError(
            message ?: requireActivity().resources.getString(R.string.general_error_message)
        )
    }
}