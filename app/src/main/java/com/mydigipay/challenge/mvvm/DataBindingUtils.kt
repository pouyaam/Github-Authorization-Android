package com.mydigipay.challenge.mvvm

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.mydigipay.challenge.infrastructure.data.model.api.repositorysearch.ItemRepository
import com.mydigipay.challenge.mvvm.repositorysearchfragment.adapter.RepositoryAdapter

/**
 * Created by Mahdi Zare Tahghigh Doost on 6/3/2020.
 *
 * mahdiZTD@gmail.com
 */
object DataBindingUtils {

    @JvmStatic
    @BindingAdapter("repositoryAdapter")
    fun addSourceAdapter(recyclerView: RecyclerView, repositories: MutableList<ItemRepository>?) {
        if (repositories != null) {
            val adapter = recyclerView.adapter as RepositoryAdapter?
            adapter?.clearItems()
            adapter?.addItems(repositories)
        }
    }
}