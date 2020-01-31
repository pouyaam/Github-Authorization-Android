package com.mydigipay.challenge.presentation.ui.github.detail

import android.view.LayoutInflater
import android.view.ViewGroup
import com.mydigipay.challenge.github.databinding.ItemCommitBinding
import com.mydigipay.challenge.presentation.core.BaseRecyclerAdapter
import com.mydigipay.challenge.presentation.core.BaseViewHolder
import com.mydigipay.challenge.presentation.model.CommitItemModel

class DetailAdapter : BaseRecyclerAdapter<CommitItemModel>() {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): BaseViewHolder<CommitItemModel> = CommitViewHolder(
        ItemCommitBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
    )
}