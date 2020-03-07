package com.mydigipay.challenge.utils

import android.os.Handler
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager

private const val STARTING_PAGE_INDEX = 1

/**
 * Provides feature for recyclerViews that do action on hitting the end of item list.
 * Users should override onLoadMore() and provide action performed on loading more
 */
abstract class EndlessRecyclerViewScrollListener(
    layoutManager: RecyclerView.LayoutManager,
    private val initialPage: Int = STARTING_PAGE_INDEX,
    visibleThreshold: Int = 2
) : RecyclerView.OnScrollListener() {

    // The minimum amount of items to have below your current scroll position
    // before loading more.
    private var visibleThreshold = 2

    // The current offset index of data you have loaded
    private var currentPage = initialPage

    // The total number of items in the dataSet after the last load
    private var previousTotalItemCount = 0

    // True if we are still waiting for the last set of data to load.
    private var loading = true

    // This is a lock that iff scroller fails multiple times then
    // it prevents from decreasing number page
    private var alreadyDecreasedPageForFailure = false

    abstract fun onLoadMore(page: Int)

    private var mLayoutManager: RecyclerView.LayoutManager = layoutManager

    init {
        when (layoutManager) {
            is LinearLayoutManager ->
                this.visibleThreshold = visibleThreshold
            is GridLayoutManager ->
                this.visibleThreshold = visibleThreshold * layoutManager.spanCount
            is StaggeredGridLayoutManager ->
                this.visibleThreshold = visibleThreshold * layoutManager.spanCount
        }
    }

    private fun getLastVisibleItem(lastVisibleItemPositions: IntArray): Int {
        var maxSize = 0
        for (i in lastVisibleItemPositions.indices) {
            if (i == 0) {
                maxSize = lastVisibleItemPositions[i]
            } else if (lastVisibleItemPositions[i] > maxSize) {
                maxSize = lastVisibleItemPositions[i]
            }
        }
        return maxSize
    }

    // This happens many times a second during a scroll, so be wary of the code you place here.
    // We are given a few useful parameters to help us work out if we need to load some more data,
    // but first we check if we are waiting for the previous load to finish.
    override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
        var lastVisibleItemPosition = 0
        val totalItemCount = mLayoutManager.itemCount

        when (mLayoutManager) {
            is StaggeredGridLayoutManager -> {
                val lastVisibleItemPositions =
                    (mLayoutManager as StaggeredGridLayoutManager).findLastVisibleItemPositions(null)
                // get maximum element within the list
                lastVisibleItemPosition = getLastVisibleItem(lastVisibleItemPositions)
            }
            is GridLayoutManager -> lastVisibleItemPosition =
                (mLayoutManager as GridLayoutManager).findLastVisibleItemPosition()
            is LinearLayoutManager -> lastVisibleItemPosition =
                (mLayoutManager as LinearLayoutManager).findLastVisibleItemPosition()
        }

        // If it’s still loading, we check to see if the dataSet count has
        // changed, if so we conclude it has finished loading and update the current page
        // number and total item count.

        // If it isn’t currently loading, we check to see if we have breached
        // the visibleThreshold and need to reload more data.
        // If we do need to reload some more data, we execute onLoadMore to fetch the data.
        // threshold should reflect how many total columns there are too

        // If it’s still loading, we check to see if the dataSet count has
        // changed, if so we conclude it has finished loading and update the current page
        // number and total item count.
        if (loading && totalItemCount > previousTotalItemCount) {
            loading = false
            previousTotalItemCount = totalItemCount
        }

        // If it isn’t currently loading, we check to see if we have breached
        // the visibleThreshold and need to reload more data.
        // If we do need to reload some more data, we execute onLoadMore to fetch the data.
        // threshold should reflect how many total columns there are too
        if (!loading && lastVisibleItemPosition + visibleThreshold > totalItemCount) {
            onLoadMore(++currentPage)
            alreadyDecreasedPageForFailure = false
            loading = true
        }
    }

    // Call whenever performing new searches
    fun resetState() {
        currentPage = initialPage
        previousTotalItemCount = 0
        loading = true
        scrollToTop()
    }

    fun failed() {
        // Set loading to false that user can load it again
        Handler().postDelayed({
            loading = false
        }, 1000)
        if (!alreadyDecreasedPageForFailure) {
            currentPage--
            alreadyDecreasedPageForFailure = true
        }
    }

    private fun scrollToTop() {
        when (mLayoutManager) {
            is LinearLayoutManager ->
                (mLayoutManager as LinearLayoutManager).scrollToPositionWithOffset(0, 0)
            is GridLayoutManager ->
                (mLayoutManager as GridLayoutManager).scrollToPositionWithOffset(0, 0)
            is StaggeredGridLayoutManager ->
                (mLayoutManager as StaggeredGridLayoutManager).scrollToPositionWithOffset(0, 0)
        }
    }

}