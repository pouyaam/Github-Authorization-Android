package com.mydigipay.challenge.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.mydigipay.challenge.R
import com.mydigipay.challenge.base.BaseAdapter
import com.mydigipay.challenge.data.models.User
import com.mydigipay.challenge.databinding.ItemUserBinding

class UserAdapter(
    private val itemClickCallback: ((User) -> Unit)? = null
) : BaseAdapter<User, ItemUserBinding>(User.DIFF_CALLBACK, itemClickCallback) {

    override fun createBinding(parent: ViewGroup): ItemUserBinding {
        val binding = DataBindingUtil.inflate<ItemUserBinding>(
            LayoutInflater.from(parent.context),
            R.layout.item_user,
            parent,
            false
        )
        binding.root.setOnClickListener {
            binding.user?.let {
                itemClickCallback?.invoke(it)
            }
        }
        return binding
    }

    override fun bind(binding: ItemUserBinding, item: User?) {
        binding.user = item
    }
}