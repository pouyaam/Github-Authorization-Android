package com.mydigipay.challenge.presentation.ui.github.detail

import com.mydigipay.challenge.github.BR
import com.mydigipay.challenge.github.databinding.ItemCommitBinding
import com.mydigipay.challenge.presentation.core.BaseViewHolder
import com.mydigipay.challenge.presentation.model.CommitItemModel

class CommitViewHolder(
    private val binding: ItemCommitBinding
) : BaseViewHolder<CommitItemModel>(binding, null) {

    override fun bind(itemData: CommitItemModel) {
        binding.setVariable(BR.itemData, itemData)
        binding.setVariable(BR.itemClickListener, itemClickListener)
    }
}