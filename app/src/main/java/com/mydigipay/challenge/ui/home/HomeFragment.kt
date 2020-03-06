package com.mydigipay.challenge.ui.home

import androidx.lifecycle.Observer
import com.mydigipay.challenge.base.BaseFragment
import com.mydigipay.challenge.github.R
import com.mydigipay.challenge.github.databinding.FragmentHomeBinding
import com.mydigipay.challenge.utils.ktx.openUrl
import org.koin.androidx.viewmodel.ext.android.viewModel

class HomeFragment : BaseFragment<HomeViewModel, FragmentHomeBinding>() {

    override val viewModel: HomeViewModel by viewModel()

    override val layoutRes: Int = R.layout.fragment_home

    override fun configEvents() {
        val code = activity?.intent?.data?.getQueryParameter("code")
        viewModel.setCode(code)
    }

    override fun bindObservables() {
        viewModel.openLink.observe(this, Observer {
            activity?.openUrl(it)
        })
    }

    override fun initBinding() {
        binding?.apply {
            lifecycleOwner = viewLifecycleOwner
            vm = viewModel
            executePendingBindings()
        }
    }
}