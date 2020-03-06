package com.mydigipay.challenge.ui.profile

import com.mydigipay.challenge.R
import com.mydigipay.challenge.base.BaseFragment
import com.mydigipay.challenge.databinding.FragmentProfileBinding
import com.mydigipay.challenge.util.FragmentOnBackPressed
import com.mydigipay.challenge.util.ktx.SwipeState
import com.mydigipay.challenge.util.ktx.onSwipe
import kotlinx.android.synthetic.main.fragment_profile.*
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel

class ProfileFragment : BaseFragment<ProfileViewModel, FragmentProfileBinding>(),
    FragmentOnBackPressed {

    override val viewModel: ProfileViewModel by viewModel()
    override val layoutRes: Int = R.layout.fragment_profile
    private val adapter: ProfileItemAdapter by inject()

    override fun oneTimeEvent() {
        getHome()?.toggleBottomSection(false)
        info_list.adapter = adapter
        scroller_view.onSwipe { state ->
            when (state) {
                SwipeState.DOWN -> edit_fab.hide()
                SwipeState.UP -> edit_fab.show()
            }
        }
    }

    override fun initBinding() {
        binding?.apply {
            lifecycleOwner = viewLifecycleOwner
            vm = viewModel
            executePendingBindings()
        }
    }

    override fun onBackPressed() = viewModel.onBackPressed().also {
        getHome()?.toggleBottomSection(true)
    }
}