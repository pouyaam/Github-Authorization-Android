package com.mydigipay.challenge.presentation.github.user

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.mydigipay.challenge.app.ViewModelProviderFactory
import com.mydigipay.challenge.app.component
import com.mydigipay.challenge.presentation.github.R
import com.mydigipay.challenge.presentation.github.databinding.FragmentUserProfileBinding
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import kotlinx.android.synthetic.main.fragment_user_profile.*
import javax.inject.Inject

class UserProfileFragment : Fragment() {

    private lateinit var compositeDisposable: CompositeDisposable

    @Inject
    lateinit var factory: ViewModelProviderFactory
    lateinit var viewModel: UserProfileViewModel
    private lateinit var binding: FragmentUserProfileBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_user_profile, container, false)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        compositeDisposable = CompositeDisposable()
        component.viewModelProviderFactory.create().inject(this)
        viewModel = ViewModelProvider(this, factory)[UserProfileViewModel::class.java]

        initDataInteraction(savedInstanceState)


    }

    private fun initDataInteraction(savedInstanceState: Bundle?) {
        if (savedInstanceState == null){
            viewModel.fetchUserInfo()
        }
        viewModel.getState()
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe {
                handleState(it)
            }.let {
                compositeDisposable.add(it)
            }

    }

    private fun handleState(state: UserProfileFragmentState) {
        when (state) {
            is UserProfileFragmentState.GotUser -> {
                loading.hide()
                binding.user = state.user
            }
            is UserProfileFragmentState.Error -> {
                Toast.makeText(context, getString(R.string.user_profile_error), Toast.LENGTH_LONG)
                    .show()
                findNavController().navigateUp()
            }
            is UserProfileFragmentState.Loading -> {
                loading.show()
            }
        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        compositeDisposable.dispose()
    }
}