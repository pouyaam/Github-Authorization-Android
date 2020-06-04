package com.mydigipay.challenge.mvvm.repocommitsfragment.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.mydigipay.challenge.databinding.ItemGithubRepositoryBinding
import com.mydigipay.challenge.databinding.ItemRepositoryCommitsBinding
import com.mydigipay.challenge.infrastructure.data.model.api.repositorycommit.ResponseRepositoryCommits
import com.mydigipay.challenge.infrastructure.data.model.api.repositorysearch.ItemRepository
import com.mydigipay.challenge.mvvm.base.BaseViewHolder

/**
 * Created by Mahdi Zare Tahghigh Doost on 6/4/2020.
 *
 * mahdiZTD@gmail.com
 */
class CommitAdapter(private val commits: MutableList<ResponseRepositoryCommits>) :
    RecyclerView.Adapter<CommitAdapter.CommitViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CommitViewHolder =
        CommitViewHolder(
            ItemRepositoryCommitsBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

    override fun getItemCount(): Int = commits.size

    override fun onBindViewHolder(holder: CommitViewHolder, position: Int) =
        holder.onBind(position)

    fun addItems(commits: MutableList<ResponseRepositoryCommits>) {
        this.commits.clear()
        this.commits.addAll(commits)
        notifyDataSetChanged()
    }

    fun clearItems() {
        this.commits.clear()
    }

    inner class CommitViewHolder(val mBinding: ItemRepositoryCommitsBinding) :
        BaseViewHolder(mBinding.root) {
        override fun onBind(position: Int) {
            mBinding.vm = CommitItemViewModel(commits[position])
            mBinding.executePendingBindings()
        }
    }

}