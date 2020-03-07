package com.mydigipay.challenge.ui.search.detail

import androidx.navigation.fragment.navArgs
import com.mydigipay.challenge.base.BaseFragment
import com.mydigipay.challenge.github.R
import com.mydigipay.challenge.github.databinding.FragmentDetailBinding
import com.mydigipay.challenge.utils.EndlessRecyclerViewScrollListener
import kotlinx.android.synthetic.main.fragment_detail.*
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel

class DetailFragment : BaseFragment<DetailViewModel, FragmentDetailBinding>() {

    override val viewModel: DetailViewModel by viewModel()
    private val adapter: CommitAdapter by inject()
    private val args: DetailFragmentArgs by navArgs()

    private var endlessScroller: EndlessRecyclerViewScrollListener? = null

    override val layoutRes: Int = R.layout.fragment_detail

    override fun configEvents() {
        viewModel.setGitRepo(args.repository)
        list_commit?.apply {
            adapter = this@DetailFragment.adapter
            layoutManager?.let { layoutManager ->
                endlessScroller =
                    object : EndlessRecyclerViewScrollListener(layoutManager) {
                        override fun onLoadMore(page: Int) {
                            viewModel.getCommits(page)
                        }
                    }.also { endlessScroller ->
                        addOnScrollListener(endlessScroller)
                    }
            }
        }
    }

    override fun bindObservables() {
    }

    override fun initBinding() {
        binding?.apply {
            lifecycleOwner = viewLifecycleOwner
            vm = viewModel
            executePendingBindings()
        }
    }
}