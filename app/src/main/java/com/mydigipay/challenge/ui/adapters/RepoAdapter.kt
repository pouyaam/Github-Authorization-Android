package com.mydigipay.challenge.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.mydigipay.challenge.R
import com.mydigipay.challenge.base.BaseAdapter
import com.mydigipay.challenge.data.models.Repo
import com.mydigipay.challenge.databinding.ItemRepoBinding


class RepoAdapter(private val itemClickCallback: ((Repo) -> Unit)? = null) :
    BaseAdapter<Repo, ItemRepoBinding>(Repo.DIFF_CALLBACK, itemClickCallback) {

    override fun createBinding(parent: ViewGroup): ItemRepoBinding {
        val binding = DataBindingUtil.inflate<ItemRepoBinding>(
            LayoutInflater.from(parent.context),
            R.layout.item_repo,
            parent,
            false
        )
        binding.root.setOnClickListener {
            binding.repo?.let {
                itemClickCallback?.invoke(it)
            }
        }
        return binding
    }

    override fun bind(binding: ItemRepoBinding, item: Repo) {
        binding.repo = item
    }
}