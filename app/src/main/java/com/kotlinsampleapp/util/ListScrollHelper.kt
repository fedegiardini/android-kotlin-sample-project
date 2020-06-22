package com.kotlinsampleapp.util

import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class ListScrollHelper : RecyclerView.OnScrollListener() {
    interface ScrollListener {
        fun onScrolledDown()

        fun onTopReached()

        fun onLoadRequested()
    }

    var scrollListener: ScrollListener? = null

    private var isLoading = false

    fun clearLoading() {
        isLoading = false
    }

    override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
        if (newState == RecyclerView.SCROLL_STATE_IDLE) {
            val layoutManager = recyclerView.layoutManager
            if (layoutManager !is LinearLayoutManager) return

            if (layoutManager.findFirstVisibleItemPosition() == 0) {
                scrollListener?.onTopReached()
            }
        }
    }

    override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
        if (dy <= 0) return

        scrollListener?.onScrolledDown()

        val layoutManager = recyclerView.layoutManager
        if (layoutManager !is LinearLayoutManager) return

        val visibleItemCount = layoutManager.childCount
        val totalItemCount = layoutManager.itemCount
        val firstVisibleItem = layoutManager.findFirstVisibleItemPosition()

        if (!isLoading && (totalItemCount - visibleItemCount) <= (firstVisibleItem + 5)) {
            isLoading = true

            scrollListener?.onLoadRequested()
        }
    }
}
