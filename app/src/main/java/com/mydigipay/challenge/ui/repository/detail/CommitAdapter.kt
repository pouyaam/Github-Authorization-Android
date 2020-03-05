package com.mydigipay.challenge.ui.repository.detail


import androidx.recyclerview.widget.DiffUtil
import com.mydigipay.challenge.R
import com.mydigipay.challenge.base.BaseAdapter
import com.mydigipay.challenge.data.model.CommitDetail

class CommitAdapter : BaseAdapter<CommitDetail>(DIFF_CALLBACK) {

    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<CommitDetail>() {
            override fun areItemsTheSame(oldItem: CommitDetail, newItem: CommitDetail): Boolean {
                return oldItem.nodeId == newItem.nodeId
            }

            override fun areContentsTheSame(oldItem: CommitDetail, newItem: CommitDetail): Boolean {
                return oldItem == newItem
            }
        }
    }

    override fun getItemViewType(position: Int): Int {
        return R.layout.item_commit
    }

}