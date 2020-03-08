package com.mydigipay.challenge.ui.search.view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.navigation.fragment.FragmentNavigator
import androidx.recyclerview.widget.RecyclerView
import com.mydigipay.challenge.dataaccess.model.ResponseProjectItem
import com.mydigipay.challenge.github.BR
import com.mydigipay.challenge.github.R
import com.mydigipay.challenge.ui.search.viewmodel.SearchItemViewModel

class SearchItemAdapter(
    private val openDetail: (item: ResponseProjectItem, extras: FragmentNavigator.Extras) -> Unit
) : RecyclerView.Adapter<SearchItemAdapter.ItemViewHolder>() {

    var items = mutableListOf<ResponseProjectItem>()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    inner class ItemViewHolder(private val viewModel: ViewDataBinding) :
        RecyclerView.ViewHolder(viewModel.root) {
        fun bind(item: ResponseProjectItem) {
            viewModel.setVariable(BR.vm, SearchItemViewModel(item, openDetail))
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