package com.mydigipay.challenge.ui.authorize

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import com.mydigipay.challenge.R

class AuthorizeFragment : Fragment() {

    companion object {
        fun newInstance() = AuthorizeFragment()
    }

    private lateinit var viewModel: AuthorizeViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.authorize_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(AuthorizeViewModel::class.java)
        // TODO: Use the ViewModel
    }

}
