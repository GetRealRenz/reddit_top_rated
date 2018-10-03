package com.getrealrenz.redditbrowser.presentation.top.common

import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import io.reactivex.processors.PublishProcessor

class EndlessScrollListener(var layoutManager: LinearLayoutManager,
                            var paginator: PublishProcessor<Int>) : RecyclerView.OnScrollListener() {
    private val VISIBLE_THRESHOLD = 1
    private var pageNumber = 0
    private var totalItemCount = 0
    private var lastVisibleItem = 0
    private var isLoading = false
    override fun onScrolled(recyclerView: RecyclerView?, dx: Int, dy: Int) {
        super.onScrolled(recyclerView, dx, dy)
        totalItemCount = layoutManager.itemCount
        lastVisibleItem = layoutManager.findLastVisibleItemPosition()
        if (isLoading.not() && totalItemCount <= (lastVisibleItem + VISIBLE_THRESHOLD)) {
            pageNumber += totalItemCount
            paginator.onNext(pageNumber)
            startLoading()
        }

    }

    fun startLoading() {
        isLoading = true
    }

    fun cancelLoading() {
        isLoading = false
    }
}