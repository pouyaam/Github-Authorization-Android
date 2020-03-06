package com.mydigipay.challenge.search

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.mydigipay.challenge.github.R
import com.mydigipay.challenge.search.repository.model.RepositoryModel
import kotlinx.android.synthetic.main.repository_item.view.*

class RepositoryAdapter(
    private val mList: List<RepositoryModel>,
    val mContext: Context?,
    val click: (RepositoryModel) -> Unit
) : RecyclerView.Adapter<RepositoryAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.repository_item, parent, false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(mList[position])
    }

    override fun getItemCount(): Int = mList.size

    inner class ViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {
        fun bind(repository: RepositoryModel) {
            with(repository) {
                view.repositoryItemNameTV.text = name
                view.repositoryItemDescriptionTV.text = description
                view.repositoryItemLanguageTV.text = language
                view.repositoryItemStarTV.text = starCount.toString()
                view.repositoryItemOwnerTV.text = owner.login

                mContext?.let {
                    Glide.with(it)
                        .load(owner.avatarUrl)
                        .circleCrop()
                        .into(view.repositoryItemAvatarIV)
                }
            }

            view.repositoryItemLayout.setOnClickListener {
                click(repository)
            }
        }
    }
}