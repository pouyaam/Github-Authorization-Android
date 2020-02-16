package com.mydigipay.challenge.ui.profile

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.mydigipay.challenge.github.R
import org.koin.android.viewmodel.ext.android.viewModel

class ProfileFragment : Fragment() {


    private val viewModel by viewModel<ProfileViewModel>()


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_profile, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        viewModel.profileState.observe(viewLifecycleOwner, Observer { state ->

        })
    }

}