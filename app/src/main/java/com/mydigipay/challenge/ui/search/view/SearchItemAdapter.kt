package com.mydigipay.challenge.ui.search.view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.navigation.NavController
import androidx.recyclerview.widget.RecyclerView
import com.mydigipay.challenge.dataaccess.model.ResponseProjectItem
import com.mydigipay.challenge.github.BR
import com.mydigipay.challenge.github.R
import com.mydigipay.challenge.ui.search.viewmodel.SearchItemViewModel

class SearchItemAdapter : RecyclerView.Adapter<SearchItemAdapter.ItemViewHolder>() {

    lateinit var navigator: NavController

    var items = mutableListOf<ResponseProjectItem>()
        set(value) {
            field = value
            if (value.isEmpty())
                items.clear()
            else
                items.addAll(value)
            notifyDataSetChanged()
        }

    fun setData(data: MutableList<ResponseProjectItem>) {
        if (data.isEmpty())
            items = data
        else
            items.addAll(data)
        notifyDataSetChanged()
    }


    inner class ItemViewHolder(private val viewModel: ViewDataBinding) :
        RecyclerView.ViewHolder(viewModel.root) {
        fun bind(item: ResponseProjectItem) {
            viewModel.setVariable(BR.vm, SearchItemViewModel(item, navigator))
            viewModel.executePendingBindings()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding: ViewDataBinding =
            DataBindingUtil.inflate(layoutInflater, R.layout.search_fragment_item, parent, false)
        return ItemViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        holder.bind(items[position])
    }

}