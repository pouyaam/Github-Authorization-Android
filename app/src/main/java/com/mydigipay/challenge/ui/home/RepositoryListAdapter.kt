package com.mydigipay.challenge.ui.home

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.mydigipay.challenge.github.R
import com.mydigipay.challenge.network.repository.Repository
import kotlinx.android.synthetic.main.item_repository_list.view.*


class RepositoryListAdapter(
    val context: Context,
    items: MutableList<Repository> = mutableListOf(),
    val onRepositoryClicked: (Repository) -> Unit

) : RecyclerView.Adapter<RepositoryListAdapter.RepositoryHolder>(), View.OnClickListener {

    var items: MutableList<Repository> = items
        set(value) {
            if (value == field)
                return
            field = value
            notifyDataSetChanged()
        }

    inner class RepositoryHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        init {
            itemView.setOnClickListener(this@RepositoryListAdapter)
        }

        fun bind(repo: Repository, position: Int) {
            itemView.txtRepoName.text = repo.fullName
            itemView.tag = position
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RepositoryHolder {

        return RepositoryHolder(parent.inflater(R.layout.item_repository_list))
    }

    private fun ViewGroup.inflater(layoutId: Int): View {
        return LayoutInflater.from(context).inflate(
            layoutId,
            this,
            false
        )
    }

    override fun getItemCount() = items.size

    @Suppress("UNCHECKED_CAST")
    override fun onBindViewHolder(holder: RepositoryHolder, position: Int) {
        val item = items[position]
        holder.bind(item, position)
    }

    override fun onClick(v: View?) {
        val position = v!!.tag.toString().toInt()
        val item = items[position]
        onRepositoryClicked(item)
    }


}