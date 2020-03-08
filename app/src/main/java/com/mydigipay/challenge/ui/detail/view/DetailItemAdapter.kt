package com.mydigipay.challenge.ui.detail.view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import com.mydigipay.challenge.dataaccess.model.ResponseCommitItem
import com.mydigipay.challenge.github.BR
import com.mydigipay.challenge.github.R
import com.mydigipay.challenge.ui.detail.viewmodel.DetailItemViewModel

class DetailItemAdapter : RecyclerView.Adapter<DetailItemAdapter.ItemViewHolder>() {

    var data = mutableListOf<ResponseCommitItem>()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    inner class ItemViewHolder(private val viewModel: ViewDataBinding) :
        RecyclerView.ViewHolder(viewModel.root) {
        fun bind(item: ResponseCommitItem) {
            viewModel.setVariable(BR.vm, DetailItemViewModel(item))
            viewModel.executePendingBindings()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding: ViewDataBinding =
            DataBindingUtil.inflate(layoutInflater, R.layout.detail_fragment_item, parent, false)
        return ItemViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return data.size
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        holder.bind(data.get(position))
    }

}