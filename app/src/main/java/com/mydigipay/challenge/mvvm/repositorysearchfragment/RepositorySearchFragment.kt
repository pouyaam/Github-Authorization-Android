package com.mydigipay.challenge.mvvm.repositorysearchfragment

import android.os.Bundle
import androidx.databinding.library.baseAdapters.BR
import com.mydigipay.challenge.R
import com.mydigipay.challenge.databinding.FragmentRepositorySearchBinding
import com.mydigipay.challenge.mvvm.base.BaseFragment
import javax.inject.Inject

/**
 * Created by Mahdi Zare Tahghigh Doost on 6/4/2020.
 *
 * mahdiZTD@gmail.com
 */
class RepositorySearchFragment :
    BaseFragment<FragmentRepositorySearchBinding, RepositorySearchViewModel>(),
    RepositorySearchNavigator {

    @Inject
    lateinit var repositorySearchViewModel: RepositorySearchViewModel

    override val bindingVariable: Int
        get() = BR.vm
    override val layoutId: Int
        get() = R.layout.fragment_repository_search
    override val mViewModel: RepositorySearchViewModel
        get() = repositorySearchViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mViewModel.setNavigator(this)
    }
}