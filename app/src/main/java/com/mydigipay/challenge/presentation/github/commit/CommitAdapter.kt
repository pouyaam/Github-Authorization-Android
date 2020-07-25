package com.mydigipay.challenge.presentation.github.commit

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.jakewharton.rxrelay2.PublishRelay
import com.mydigipay.challenge.presentation.github.R
import com.mydigipay.challenge.presentation.github.databinding.ItemCommitBinding
import com.mydigipay.challenge.presentation.github.databinding.ItemRepoBinding
import com.mydigipay.challenge.presentation.model.CommitItem
import com.mydigipay.challenge.presentation.model.RepositoryItem
import kotlinx.android.synthetic.main.item_repo.view.*

class CommitAdapter :
    ListAdapter<CommitItem, CommitAdapter.CommitViewHolder>(
        DIFF_CALLBACK()
    ) {


    class DIFF_CALLBACK : DiffUtil.ItemCallback<CommitItem>() {
        override fun areItemsTheSame(oldItem: CommitItem, newItem: CommitItem): Boolean {
            return oldItem.name.equals(newItem.name)
        }

        override fun areContentsTheSame(oldItem: CommitItem, newItem: CommitItem): Boolean {
            return oldItem.name.equals(newItem.name)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CommitViewHolder {
        return CommitViewHolder(
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                R.layout.item_commit,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: CommitViewHolder, position: Int) {
        val commit = getItem(position)
        holder.binding.commit = commit
    }


    class CommitViewHolder(val binding: ItemCommitBinding) :
        RecyclerView.ViewHolder(binding.root) {
        init {
            binding.commitMessageTv.isSelected = true
        }
    }
}