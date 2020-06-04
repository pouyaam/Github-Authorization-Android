package com.mydigipay.challenge.mvvm.profilefragment

import android.os.Bundle
import android.view.View
import androidx.databinding.library.baseAdapters.BR
import com.mydigipay.challenge.R
import com.mydigipay.challenge.databinding.FragmentProfileBinding
import com.mydigipay.challenge.mvvm.base.BaseFragment
import javax.inject.Inject

/**
 * Created by Mahdi Zare Tahghigh Doost on 6/4/2020.
 *
 * mahdiZTD@gmail.com
 */
class ProfileFragment : BaseFragment<FragmentProfileBinding, ProfileViewModel>(), ProfileNavigator {

    @Inject
    lateinit var profileViewModel: ProfileViewModel

    override val bindingVariable: Int
        get() = BR.vm
    override val layoutId: Int
        get() = R.layout.fragment_profile
    override val mViewModel: ProfileViewModel
        get() = profileViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mViewModel.setNavigator(this)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mViewModel.getProfile(ProfileFragmentArgs.fromBundle(requireArguments()).username)
    }
}