package com.mydigipay.challenge.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.mydigipay.challenge.R
import com.mydigipay.challenge.base.BaseAdapter
import com.mydigipay.challenge.data.models.CommitDetail
import com.mydigipay.challenge.databinding.ItemCommitBinding


class CommitAdapter(private val itemClickCallback: ((CommitDetail) -> Unit)? = null) :
    BaseAdapter<CommitDetail, ItemCommitBinding>(CommitDetail.DIFF_CALLBACK, itemClickCallback) {

    override fun createBinding(parent: ViewGroup): ItemCommitBinding {
        val binding = DataBindingUtil.inflate<ItemCommitBinding>(
            LayoutInflater.from(parent.context),
            R.layout.item_commit,
            parent,
            false
        )
        binding.root.setOnClickListener {
            binding.commitDetail?.let {
                itemClickCallback?.invoke(it)
            }
        }
        return binding
    }

    override fun bind(binding: ItemCommitBinding, item: CommitDetail?) {
        binding.commitDetail = item
    }
}