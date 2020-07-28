package com.mydigipay.challenge.presentation.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.mydigipay.challenge.R
import com.mydigipay.challenge.databinding.ItemErrorBinding
import com.mydigipay.challenge.databinding.ItemGithubRepoBinding
import com.mydigipay.challenge.databinding.ItemLoadingBinding
import com.mydigipay.challenge.presentation.model.ListItem
import com.mydigipay.challenge.presentation.model.ListItemType
import com.mydigipay.challenge.presentation.view.callback.ListItemCallback

/**
 * Provide a suitable constructor (depends on the kind of dataset)
 *
 * @param mCallback
 */
class GithubListAdapter(private val mCallback: ListItemCallback) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var mDataset: MutableList<ListItem> = mutableListOf()
    private var mLoadingListItem: ListItem.LoadingListItem? = null
    private var mErrorListItem: ListItem.ErrorListItem? = null

    /**
     * Create new views (invoked by the layout manager)
     *
     * @param parent
     * @param viewType
     * @return
     */
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        when (viewType) {
            ListItemType.GITHUB_REPO_ITEM.value -> {
                val binding: ItemGithubRepoBinding = DataBindingUtil.inflate(
                    LayoutInflater.from(parent.context),
                    R.layout.item_github_repo,
                    parent,
                    false
                )

                binding.callback = mCallback

                return GithubRepoItemViewHolder(binding)
            }
            ListItemType.LOADING_ITEM.value -> {
                val binding: ItemLoadingBinding = DataBindingUtil.inflate(
                    LayoutInflater.from(parent.context),
                    R.layout.item_loading,
                    parent,
                    false
                )

                binding.callback = mCallback

                return LoadingItemViewHolder(binding)
            }
            else -> {
                val binding: ItemErrorBinding = DataBindingUtil.inflate(
                    LayoutInflater.from(parent.context),
                    R.layout.item_error,
                    parent,
                    false
                )

                binding.callback = mCallback

                return ErrorItemViewHolder(binding)
            }
        }
    }

    /**
     * Replace the contents of a view (invoked by the layout manager)
     *
     *
     * Get element from your dataset at this position and
     * replace the contents of the view with that element.
     *
     * @param holder
     * @param position
     */
    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder.itemViewType) {
            ListItemType.GITHUB_REPO_ITEM.value -> {
                (holder as GithubRepoItemViewHolder).bind(mDataset[position] as ListItem.GithubRepoListItem)
            }
            ListItemType.LOADING_ITEM.value -> {
                (holder as LoadingItemViewHolder).bind(mDataset[position] as ListItem.LoadingListItem)
            }
            else -> {
                (holder as ErrorItemViewHolder).bind(mDataset[position] as ListItem.ErrorListItem)
            }
        }
    }

    /**
     * Return the size of your dataset (invoked by the layout manager)
     *
     * @return
     */
    override fun getItemCount(): Int {
        return mDataset.size
    }

    /**
     * Return the view type of the item at `position` for the purposes
     * of view recycling.
     *
     * @return
     */
    override fun getItemViewType(position: Int): Int {
        return mDataset[position].viewType.value
    }

    /**
     * Provide a reference to the views for each data item
     *
     * Complex data items may need more than one view per item, and
     * you provide access to all the views for a data item in a view holder.
     */

    class GithubRepoItemViewHolder(private val binding: ItemGithubRepoBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(githubRepoItem: ListItem.GithubRepoListItem) {
            with(binding) {
                item = githubRepoItem
                executePendingBindings()
            }
        }
    }

    class LoadingItemViewHolder(private val binding: ItemLoadingBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(loadingItem: ListItem.LoadingListItem) {
            with(binding) {
                item = loadingItem
                executePendingBindings()
            }
        }
    }

    class ErrorItemViewHolder(private val binding: ItemErrorBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(errorItem: ListItem.ErrorListItem) {
            with(binding) {
                item = errorItem
                executePendingBindings()
            }
        }
    }

    /****************************************************
     * Functionality to update dataset
     ***************************************************/

    fun showLoadingItem(loadingItem: ListItem.LoadingListItem) {
        mLoadingListItem = loadingItem
        insertItem(loadingItem, mDataset.size)
    }

    fun hideLoadingItem() {
        mLoadingListItem?.let {
            removeItem(it)
        }
        mLoadingListItem = null
    }

    fun showErrorItem(errorItem: ListItem.ErrorListItem) {
        mErrorListItem = errorItem
        insertItem(errorItem, mDataset.size)
    }

    fun hideErrorItem() {
        mErrorListItem?.let {
            removeItem(it)
        }
        mErrorListItem = null
    }

    fun addItems(dataset: List<ListItem>) {
        val beforeSize = mDataset.size
        mDataset.addAll(dataset)
        notifyItemRangeInserted(beforeSize, dataset.size)
    }

    fun clearItems() {
        mDataset.clear()
        notifyDataSetChanged()
    }

    fun insertItem(item: ListItem, position: Int) {
        mDataset.add(position, item)
        notifyItemInserted(position)
    }

    fun removeItem(item: ListItem) {
        val position = mDataset.indexOf(item)
        mDataset.removeAt(position)
        notifyItemRemoved(position)
    }

    fun changeItem(item: ListItem) {
        val position = mDataset.indexOf(item)
        notifyItemChanged(position)
    }

    fun getDataset(): List<ListItem>? {
        return mDataset
    }

    fun changeDataset(dataset: ArrayList<ListItem>) {
        mDataset = dataset
        notifyDataSetChanged()
    }

    fun setDataset(dataset: ArrayList<ListItem>) {
        if (mDataset.isEmpty()) {
            mDataset.addAll(dataset)
            notifyItemRangeInserted(0, dataset.size)
        }
        else {
            val result = DiffUtil.calculateDiff(ListItemDistinguisher(mDataset, dataset))

            mDataset = dataset
            result.dispatchUpdatesTo(this)
        }
    }

    companion object {
        val TAG = GithubListAdapter::class.java.simpleName
    }
}
