package com.mydigipay.challenge.commit.repository

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.mydigipay.challenge.commit.repository.model.CommitResponseModel
import com.mydigipay.challenge.github.R
import com.mydigipay.challenge.search.repository.model.RepositoryModel
import kotlinx.android.synthetic.main.commit_item.view.*
import kotlinx.android.synthetic.main.repository_item.view.*

class CommitAdapter(
    private val mList: List<CommitResponseModel>,
    val mContext: Context?,
    val click: (CommitResponseModel) -> Unit
) : RecyclerView.Adapter<CommitAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.commit_item, parent, false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(mList[position])
    }

    override fun getItemCount(): Int = mList.size

    inner class ViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {
        fun bind(commitModel: CommitResponseModel) {
            with(commitModel) {
                view.commitItemNameTV.text = author?.login ?: ""
                view.commitItemDescriptionTV.text = commit.message
                view.commitItemCommentTV.text = commit.commentCount.toString()
                view.commitItemDateTV.text = commit.author.date

                mContext?.let {
                    Glide.with(it)
                        .load(author?.avatarUrl)
                        .circleCrop()
                        .into(view.commitItemAvatarIV)
                }
            }

            view.commitItemLayout.setOnClickListener {
                click(commitModel)
            }
        }
    }
}