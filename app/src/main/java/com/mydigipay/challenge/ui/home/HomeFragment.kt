package com.mydigipay.challenge.ui.home

import com.mydigipay.challenge.R
import com.mydigipay.challenge.base.BaseFragment
import com.mydigipay.challenge.databinding.FragmentHomeBinding
import org.koin.android.ext.android.inject


class HomeFragment : BaseFragment<HomeViewModel, FragmentHomeBinding>() {

    override val viewModel: HomeViewModel by inject()
    override val layoutId: Int = R.layout.fragment_home
    override fun setBindingVar() {
    }
}