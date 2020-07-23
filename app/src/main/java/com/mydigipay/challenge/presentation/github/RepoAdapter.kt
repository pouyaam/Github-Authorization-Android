package com.mydigipay.challenge.presentation.github

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.jakewharton.rxrelay2.PublishRelay
import com.mydigipay.challenge.presentation.github.databinding.ItemRepoBinding
import com.mydigipay.challenge.presentation.model.RepositoryItem
import kotlinx.android.synthetic.main.item_repo.view.*

class RepoAdapter :
    ListAdapter<RepositoryItem, RepoAdapter.RepoViewHolder>(
        DIFF_CALLBACK()
    ) {

    private val onItemClick = PublishRelay.create<RepositoryItem>()

    class DIFF_CALLBACK : DiffUtil.ItemCallback<RepositoryItem>() {
        override fun areItemsTheSame(oldItem: RepositoryItem, newItem: RepositoryItem): Boolean {
            return oldItem.fullName.equals(newItem.fullName)
        }

        override fun areContentsTheSame(oldItem: RepositoryItem, newItem: RepositoryItem): Boolean {
            return oldItem.fullName.equals(newItem.fullName)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RepoViewHolder {
        return RepoViewHolder(
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                R.layout.item_repo,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: RepoViewHolder, position: Int) {
        val repo = getItem(position)
        holder.binding.repo = repo
        holder.itemView.setOnClickListener {
            onItemClick.accept(repo)
        }
    }

    fun selectedRepo() = onItemClick.hide()

    class RepoViewHolder(val binding: ItemRepoBinding) :
        RecyclerView.ViewHolder(binding.root) {
        init {
            binding.root.fullname_tv.isSelected = true
        }
    }
}