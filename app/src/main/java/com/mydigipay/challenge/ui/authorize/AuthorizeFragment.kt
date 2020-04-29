package com.mydigipay.challenge.ui.authorize

import androidx.recyclerview.widget.LinearLayoutManager
import com.mydigipay.challenge.R
import com.mydigipay.challenge.auth.AuthenticationUtil
import com.mydigipay.challenge.base.BaseFragment
import com.mydigipay.challenge.databinding.FragmentAuthorizeBinding
import com.mydigipay.challenge.ui.adapters.UserAdapter
import org.koin.android.ext.android.inject

class AuthorizeFragment : BaseFragment<AuthorizeViewModel, FragmentAuthorizeBinding>() {

    override val viewModel: AuthorizeViewModel by inject()
    override val layoutId = R.layout.fragment_authorize

    private val authUtils: AuthenticationUtil by inject()
    private val userAdapter = UserAdapter {

    }

    override fun setBindingVar() {
        configList()
        binding.vm = viewModel
        viewModel.loadUsers(authUtils)

    }

    private fun configList() {
        binding.accountsListRV.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = userAdapter
        }
    }


}
