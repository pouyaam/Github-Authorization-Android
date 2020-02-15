package com.mydigipay.challenge.ui.home

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.mydigipay.challenge.extentions.showToast
import com.mydigipay.challenge.github.R
import kotlinx.android.synthetic.main.fragment_home.*
import org.koin.android.viewmodel.ext.android.viewModel

class HomeFragment : Fragment() {


    companion object {
        const val CLIENT_ID = "Iv1.c47d8ef013a73506"
        const val CLIENT_SECRET = "608e0cd66e141bb63804b07c2a866f472b188aa2"
        const val REDIRECT_URI = "omidgitapp://authorization/"
    }

    private val viewModel by viewModel<HomeViewModel>()

    private val adapter by lazy {
        RepositoryListAdapter(context!!, onRepositoryClicked = {

        })
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        viewModel.code = HomeFragmentArgs.fromBundle(arguments!!).code

        viewModel.data.observe(viewLifecycleOwner, Observer { state ->
            repositoriesContainer.isVisible = state.requiredCode.not()
            authorize.isVisible = state.requiredCode
            state.repositories.takeIf { it.isNotEmpty() }?.let {
                adapter.items = it.toMutableList()
                showToast("Token: $it")
                Log.i("GitToken", it.toString())
            }
        })
        recyclerRepositories.apply {
            layoutManager = LinearLayoutManager(context!!)
            adapter = this@HomeFragment.adapter
        }


        authorize.setOnClickListener { view ->
            val url =
                "https://github.com/login/oauth/authorize?client_id=$CLIENT_ID&redirect_uri=$REDIRECT_URI&scope=repo user&state=0"
            val i = Intent(Intent.ACTION_VIEW)
            i.data = Uri.parse(url)
            startActivity(i)
        }
    }

}