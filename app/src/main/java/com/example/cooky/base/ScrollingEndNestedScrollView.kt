package com.example.cooky.base

import androidx.core.widget.NestedScrollView

abstract class ScrollingEndNestedScrollView : NestedScrollView.OnScrollChangeListener {
    protected abstract val distanceToBottom: Int
    override fun onScrollChange(
        view: NestedScrollView?,
        scrollX: Int,
        scrollY: Int,
        oldScrollX: Int,
        oldScrollY: Int
    ) {
        view?.let {
            val bottomY = it.getChildAt(0).measuredHeight.minus(it.measuredHeight)
                if (scrollY + distanceToBottom >= bottomY && scrollY > oldScrollY) {
                    loadMore()
                }
        }
    }

    abstract fun loadMore()
}
