package com.mydigipay.challenge.ui.search.detail


import androidx.recyclerview.widget.DiffUtil
import com.mydigipay.challenge.base.BaseAdapter
import com.mydigipay.challenge.data.pojo.Commit
import com.mydigipay.challenge.github.R

class CommitAdapter : BaseAdapter<Commit>(DIFF_CALLBACK) {

    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<Commit>() {
            override fun areItemsTheSame(oldItem: Commit, newItem: Commit): Boolean {
                return oldItem.nodeId == newItem.nodeId
            }

            override fun areContentsTheSame(oldItem: Commit, newItem: Commit): Boolean {
                return oldItem == newItem
            }
        }
    }

    override fun getItemViewType(position: Int): Int {
        return R.layout.item_commit
    }

}