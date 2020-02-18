package com.mydigipay.challenge.ui.commits

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.mydigipay.challenge.github.R
import com.mydigipay.challenge.network.model.commit.CommitWrapper
import com.mydigipay.challenge.network.model.commit.Committer
import kotlinx.android.synthetic.main.item_commit_list.view.*


class CommitListAdapter(
    val context: Context,
    items: MutableList<CommitWrapper> = mutableListOf(),
    val onCommitClicked: (CommitWrapper) -> Unit

) : RecyclerView.Adapter<CommitListAdapter.CommitHolder>(), View.OnClickListener {

    var originalData: List<CommitWrapper> = items.toMutableList()
        set(value) {
            if (value == field)
                return
            field = value
            items = value.toMutableList()
        }

    fun filter(query: String) {
        if (query.isBlank()) {
            items = originalData.toMutableList()
            return
        }
        items =
            originalData.filter { it.commit.message.contains(query, true) }.toMutableList()
    }

    var items: MutableList<CommitWrapper> = items
        set(value) {
            if (value == field)
                return
            field = value
            notifyDataSetChanged()
        }

    inner class CommitHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        init {
            itemView.setOnClickListener(this@CommitListAdapter)
        }

        fun bind(commitWrapper: CommitWrapper, position: Int) {
            itemView.txtCommitMessage.text = commitWrapper.commit.message
            itemView.txtCommitSha.text = commitWrapper.sha
            itemView.txtCommitDescriptions.text = commitWrapper.commit.committer.info
            itemView.tag = position
        }
    }

    private val Committer.info: String
        get() {
            return "committed by $name on $date"
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CommitHolder {

        return CommitHolder(parent.inflater(R.layout.item_commit_list))
    }

    private fun ViewGroup.inflater(layoutId: Int): View {
        return LayoutInflater.from(context).inflate(
            layoutId,
            this,
            false
        )
    }

    override fun getItemCount() = items.size

    override fun onBindViewHolder(holder: CommitHolder, position: Int) {
        val item = items[position]
        holder.bind(item, position)
    }

    override fun onClick(v: View?) {
        val position = v!!.tag.toString().toInt()
        val item = items[position]
        onCommitClicked(item)
    }


}