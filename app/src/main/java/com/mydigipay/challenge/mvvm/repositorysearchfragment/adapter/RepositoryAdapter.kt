package com.mydigipay.challenge.mvvm.repositorysearchfragment.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.mydigipay.challenge.databinding.ItemGithubRepositoryBinding
import com.mydigipay.challenge.infrastructure.data.model.api.repositorysearch.ItemRepository
import com.mydigipay.challenge.mvvm.base.BaseViewHolder

/**
 * Created by Mahdi Zare Tahghigh Doost on 6/4/2020.
 *
 * mahdiZTD@gmail.com
 */
class RepositoryAdapter(private val repositories: MutableList<ItemRepository>) :
    RecyclerView.Adapter<RepositoryAdapter.RepositoryViewHolder>() {

    var clickListener: RepositoryAdapter.OnItemClickListener? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RepositoryViewHolder =
        RepositoryViewHolder(
            ItemGithubRepositoryBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

    override fun getItemCount(): Int = repositories.size

    override fun onBindViewHolder(holder: RepositoryViewHolder, position: Int) =
        holder.onBind(position)

    fun addItems(repositories: MutableList<ItemRepository>) {
        this.repositories.clear()
        this.repositories.addAll(repositories)
        notifyDataSetChanged()
    }

    fun clearItems() {
        this.repositories.clear()
    }

    inner class RepositoryViewHolder(val mBinding: ItemGithubRepositoryBinding) :
        BaseViewHolder(mBinding.root) {
        override fun onBind(position: Int) {
            mBinding.vm = RepositoryItemViewModel(repositories[position], clickListener)
            mBinding.executePendingBindings()
        }
    }

    interface OnItemClickListener {
        fun onItemClicked(articleUrl: String?)
    }


}