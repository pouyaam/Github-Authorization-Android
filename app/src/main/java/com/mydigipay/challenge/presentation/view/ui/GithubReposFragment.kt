package com.mydigipay.challenge.presentation.view.ui

import android.os.Bundle
import android.view.*
import android.view.animation.AnimationUtils
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.mydigipay.challenge.R
import com.mydigipay.challenge.common.help.EndlessScrollListener
import com.mydigipay.challenge.common.help.runIfNull
import com.mydigipay.challenge.databinding.FragmentGithubReposBinding
import com.mydigipay.challenge.presentation.design.MviFragment
import com.mydigipay.challenge.presentation.model.ListItem
import com.mydigipay.challenge.presentation.view.adapter.GithubListAdapter
import com.mydigipay.challenge.presentation.view.adapter.VerticalSpaceItemDecoration
import com.mydigipay.challenge.presentation.view.callback.ListItemCallback
import com.mydigipay.challenge.presentation.viewmodel.GithubReposViewModel
import com.mydigipay.challenge.presentation.viewstate.*
import com.mydigipay.challenge.presentation.viewstate.GithubReposFetchStatus.*
import javax.inject.Inject

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class GithubReposFragment :
    MviFragment<GithubReposViewState, GithubReposViewEffect, GithubReposViewEvent, GithubReposViewModel>() {

    /**
     * Values
     */

    @Inject
    internal lateinit var viewModelProviderFactory: ViewModelProvider.Factory

    private lateinit var dataBinding: FragmentGithubReposBinding
    private lateinit var githubReposAdapter: GithubListAdapter

    /**
     * Workflow
     */

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel = ViewModelProvider(requireActivity(), viewModelProviderFactory)[GithubReposViewModel::class.java]
        savedInstanceState.runIfNull {
            /* Nothing */
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        parent: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        dataBinding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_github_repos, parent, false)

        return dataBinding.root
    }

    override fun onContextItemSelected(item: MenuItem): Boolean {
        return false
    }

    override fun setupViews() {
        setupItemsListView()
        setupErrorAnnounce()
    }

    override fun setupItemsListView() {
        githubReposAdapter = GithubListAdapter(mCallback)

        val linearLayoutManager = LinearLayoutManager(context)
        dataBinding.githubRepos.apply {
            adapter = githubReposAdapter
            layoutManager = linearLayoutManager
            addItemDecoration(VerticalSpaceItemDecoration(1))
            addOnScrollListener(object : EndlessScrollListener(linearLayoutManager) {
                override fun onLoadMore(page: Int) {
                    viewModel.process(GithubReposViewEvent.OnLoadMore(page))
                }
            })
        }

        dataBinding.swipeContainer.setOnRefreshListener {
            viewModel.process(GithubReposViewEvent.OnSwipeRefresh)
        }
    }

    override fun setupErrorAnnounce() {
        dataBinding.errorAnnounce.setFactory {
            val t = TextView(context)
            t.gravity = Gravity.CENTER_VERTICAL or Gravity.CENTER_HORIZONTAL
            t.setTextColor(context!!.resources.getColor(R.color.colorTextDescription))
            t
        }
        dataBinding.errorAnnounce.inAnimation = AnimationUtils.loadAnimation(
            context,
            R.anim.slide_in_down
        )
        dataBinding.errorAnnounce.outAnimation = AnimationUtils.loadAnimation(
            context,
            R.anim.slide_out_up
        )
        dataBinding.errorAnnounce.setCurrentText("")
    }

    override fun setupObservers() {
        super.setupObservers()

        viewModel.viewStates().observe(viewLifecycleOwner, viewStateObserver)
        viewModel.viewEffects().observe(viewLifecycleOwner, viewEffectObserver)

        viewModel.process(GithubReposViewEvent.OnLoadMore(FIRST_PAGE))
    }

    override fun renderViewState(viewState: GithubReposViewState) {
        with(dataBinding) {
            this.initialLoading = viewState.fetchStatus === InitialPageFetching
            this.initialError = viewState.fetchStatus is InitialPageNotFetched
            executePendingBindings()
        }

        // Handle showing loading indicator for sequence pages
        when(viewState.fetchStatus) {
            InitialPageFetching -> {
                /* Nothing */
            }
            InitialPageFetched -> {
                githubReposAdapter.setDataset(viewState.githubRepos as ArrayList<ListItem>)
            }
            is InitialPageNotFetched -> {
                dataBinding.errorAnnounce.setText(viewState.fetchStatus.errorMessage)
            }
            SequencePageFetching -> {
                githubReposAdapter.showLoadingItem(buildLoadingItem(viewState.page))
            }
            SequencePageFetched -> {
                githubReposAdapter.hideLoadingItem()
                githubReposAdapter.setDataset(viewState.githubRepos as ArrayList<ListItem>)
            }
            is SequencePageNotFetched -> {
                githubReposAdapter.hideLoadingItem()
                githubReposAdapter.showErrorItem(buildErrorItem(viewState.fetchStatus.errorMessage, viewState.page))
            }
        }
    }

    override fun renderViewEffect(viewEffect: GithubReposViewEffect) {
        when(viewEffect) {
            is GithubReposViewEffect.NavigateToRepoCommits -> {
                findNavController().navigate(R.id.action_GithubReposFragment_to_RepoCommitsFragment)
            }
            else -> throw IllegalArgumentException("Un-expected view effect")
        }
    }

    /**
     * Products Adapter Callback
     */
    private val mCallback: ListItemCallback by lazy {
        object : ListItemCallback {
            override fun onClick(item: ListItem.GithubRepoListItem) {
                if (lifecycle.currentState.isAtLeast(Lifecycle.State.STARTED)) {
                    viewModel.process(GithubReposViewEvent.GithubRepoClicked(item))
                }
            }

            override fun onClick(item: ListItem.ErrorListItem) {
                if (lifecycle.currentState.isAtLeast(Lifecycle.State.STARTED)) {
                    viewModel.process(GithubReposViewEvent.OnLoadMore(item.page))
                }
            }
        }
    }

    companion object {

        /**
         * Use this factory method to create a new instance of this fragment.
         *
         * @return A new instance of fragment [GithubReposFragment].
         */
        fun newInstance(): GithubReposFragment {
            return GithubReposFragment()
        }

        val TAG = GithubReposFragment::class.java.simpleName
        val ID = GithubReposFragment::class.java.simpleName.hashCode()
        const val TITLE: Int = R.string.github_repos_fragment_label
    }
}
