package com.mydigipay.challenge.presentation.github.commit

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.mydigipay.challenge.app.ViewModelProviderFactory
import com.mydigipay.challenge.app.component
import com.mydigipay.challenge.presentation.github.R
import com.mydigipay.challenge.presentation.github.SearchToCommitViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import kotlinx.android.synthetic.main.fragment_commit.*
import kotlinx.android.synthetic.main.fragment_commit.errorTv
import kotlinx.android.synthetic.main.fragment_commit.loading
import kotlinx.android.synthetic.main.fragment_commit.tryAgainBtn
import javax.inject.Inject

class CommitFragment : Fragment() {

    private val repoSelectionViewModel: SearchToCommitViewModel by activityViewModels()
    private lateinit var compositeDisposable: CompositeDisposable

    @Inject
    lateinit var factory: ViewModelProviderFactory
    lateinit var viewModel: CommitViewModel
    private var lastVisibleCommit = 0
    private val stateBundlePositionKey = "POSITION_KEY"
    private lateinit var adapter: CommitAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_commit, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        compositeDisposable = CompositeDisposable()
        component.viewModelProviderFactory.create().inject(this)
        viewModel = ViewModelProvider(this, factory)[CommitViewModel::class.java]

        initViewInteraction(savedInstanceState)
        initDataInteraction(savedInstanceState)

    }

    private fun initViewInteraction(savedInstanceState: Bundle?) {
        savedInstanceState?.let {
            lastVisibleCommit = it.getInt(stateBundlePositionKey)
        }
        adapter =
            CommitAdapter()

        commitRv.layoutManager = LinearLayoutManager(requireContext())
        commitRv.adapter = adapter
        commitRv.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
                when (newState) {
                    RecyclerView.SCROLL_STATE_IDLE -> {
                        lastVisibleCommit =
                            (recyclerView.layoutManager as LinearLayoutManager).findLastCompletelyVisibleItemPosition()
                    }
                }
            }
        })
        tryAgainBtn.setOnClickListener {
            viewModel.getCommits(repoSelectionViewModel.owner, repoSelectionViewModel.repo)
        }
    }

    private fun initDataInteraction(savedInstanceState: Bundle?) {
        if (savedInstanceState == null)
            viewModel.getCommits(repoSelectionViewModel.owner, repoSelectionViewModel.repo)
        viewModel.getState()
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe {
                handleState(it)
            }.let {
                compositeDisposable.add(it)
            }
    }

    private fun handleState(state: CommitFragmentState) {
        when (state) {
            is CommitFragmentState.Error -> {
                loading.hide()
                commitRv.visibility = View.GONE
                errorTv.visibility = View.VISIBLE
                tryAgainBtn.visibility = View.VISIBLE
                errorTv.text = getString(R.string.netwrok_error)
            }
            is CommitFragmentState.GotCommits -> {
                loading.hide()
                adapter.submitList(state.commits)
                errorTv.visibility = View.GONE
                tryAgainBtn.visibility = View.GONE
                commitRv.visibility = View.VISIBLE
                commitRv.scrollToPosition(lastVisibleCommit)
            }
            is CommitFragmentState.Loading -> {
                loading.show()
                commitRv.visibility = View.GONE
                errorTv.visibility = View.GONE
                tryAgainBtn.visibility = View.GONE
            }
            is CommitFragmentState.NoCommits -> {
                loading.hide()
                commitRv.visibility = View.GONE
                tryAgainBtn.visibility = View.GONE
                errorTv.visibility = View.VISIBLE
                errorTv.text = getString(R.string.no_commit)
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        compositeDisposable.dispose()

    }

    override fun onSaveInstanceState(outState: Bundle) {
        outState.putInt(stateBundlePositionKey, lastVisibleCommit)
        super.onSaveInstanceState(outState)
    }
}