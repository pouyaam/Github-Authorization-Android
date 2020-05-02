package com.mydigipay.challenge.ui.profile

import com.mydigipay.challenge.R
import com.mydigipay.challenge.base.BaseFragment
import com.mydigipay.challenge.databinding.FragmentProfileBinding
import org.koin.android.ext.android.inject


class ProfileFragment : BaseFragment<ProfileViewModel, FragmentProfileBinding>() {

    override val viewModel: ProfileViewModel by inject()
    override val layoutId: Int = R.layout.fragment_profile

    override val needAuthentication = true

    override fun setBindingVar() {

    }
}