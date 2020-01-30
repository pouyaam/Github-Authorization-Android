package com.mydigipay.challenge.presentation.core

import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.github.mohammadsianaki.core.extenstion.hide
import com.github.mohammadsianaki.core.extenstion.show
import com.github.mohammadsianaki.core.model.Resource
import com.github.mohammadsianaki.core.ui.BaseFragment
import com.mydigipay.challenge.github.R
import kotlinx.android.synthetic.main.list_fragment.*

abstract class ListFragment<Item : ItemModel, VM : ListViewModel<Item>> :
    BaseFragment() {

    override val layoutId: Int = R.layout.list_fragment
    private var recyclerView: RecyclerView? = null
    protected abstract val recyclerAdapter: BaseRecyclerAdapter<Item>
    protected lateinit var viewModel: VM


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initUi()
    }

    protected open fun initUi() {
        recyclerView = recycler_view
        recyclerView?.apply {
            adapter = recyclerAdapter
            layoutManager = LinearLayoutManager(requireContext())
            itemAnimator = DefaultItemAnimator()
        }
    }

    override fun onDestroyView() {
        recyclerView?.adapter = null
        recyclerView = null
        super.onDestroyView()
    }

    protected fun hideRecyclerView() = recyclerView?.hide()
    protected fun showRecyclerView() = recyclerView?.show()
    protected abstract fun renderUI(resource: Resource<List<Item>>?)
    protected abstract fun handleData(data: List<Item>?)
}