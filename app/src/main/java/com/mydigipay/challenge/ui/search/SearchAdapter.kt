package com.mydigipay.challenge.ui.search

import androidx.recyclerview.widget.DiffUtil
import com.mydigipay.challenge.base.BaseAdapter
import com.mydigipay.challenge.data.pojo.Repository
import com.mydigipay.challenge.github.R

class SearchAdapter : BaseAdapter<Repository>(DIFF_CALLBACK) {

    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<Repository>() {
            override fun areItemsTheSame(oldItem: Repository, newItem: Repository): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: Repository, newItem: Repository): Boolean {
                return oldItem == newItem
            }
        }
    }

    override fun getItemViewType(position: Int) = R.layout.item_repository

}