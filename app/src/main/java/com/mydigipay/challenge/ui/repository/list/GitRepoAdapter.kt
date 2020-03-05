package com.mydigipay.challenge.ui.repository.list


import androidx.recyclerview.widget.DiffUtil
import com.mydigipay.challenge.R
import com.mydigipay.challenge.base.BaseAdapter
import com.mydigipay.challenge.data.model.GitRepo

class GitRepoAdapter : BaseAdapter<GitRepo>(DIFF_CALLBACK) {

    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<GitRepo>() {
            override fun areItemsTheSame(oldItem: GitRepo, newItem: GitRepo): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: GitRepo, newItem: GitRepo): Boolean {
                return oldItem == newItem
            }
        }
    }

    override fun getItemViewType(position: Int) = R.layout.item_repository

}