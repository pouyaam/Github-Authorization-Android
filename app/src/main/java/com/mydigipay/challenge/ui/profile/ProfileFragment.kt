package com.mydigipay.challenge.ui.profile

import com.mydigipay.challenge.base.BaseFragment
import com.mydigipay.challenge.github.R
import com.mydigipay.challenge.github.databinding.FragmentProfileBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class ProfileFragment : BaseFragment<ProfileViewModel, FragmentProfileBinding>() {

    override val viewModel: ProfileViewModel by viewModel()

    override val layoutRes: Int = R.layout.fragment_profile

    override fun configEvents() {
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