package com.mydigipay.challenge.ui.profile

import androidx.recyclerview.widget.LinearLayoutManager
import com.mydigipay.challenge.R
import com.mydigipay.challenge.base.BaseFragment
import com.mydigipay.challenge.databinding.FragmentProfileBinding
import com.mydigipay.challenge.ui.adapters.RepoAdapter
import com.mydigipay.challenge.ui.search.SearchRepositoryFragmentDirections
import com.mydigipay.challenge.util.EndlessRecyclerViewScrollListener
import org.koin.android.ext.android.inject


class ProfileFragment : BaseFragment<ProfileViewModel, FragmentProfileBinding>() {

    override val viewModel: ProfileViewModel by inject()
    override val layoutId: Int = R.layout.fragment_profile


    private val repoAdapter = RepoAdapter {

        navController.navigate(
            SearchRepositoryFragmentDirections.actionNavSearchRepositoriesToNavRepo(
                it
            )
        )
    }
    override val needAuthentication = true
    override fun setBindingVar() {
        configList()
        binding.vm = viewModel

        viewModel.setUser(authUtil.getCurrentUser())

    }

    private fun configList() {
        binding.fragmentProfileReposListRv.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = repoAdapter

            addOnScrollListener(object :
                EndlessRecyclerViewScrollListener(layoutManager as LinearLayoutManager) {
                override fun onLoadMore(page: Int, totalItemsCount: Int) {
                    viewModel.loadRepos(page)
                }
            })
        }
    }
}