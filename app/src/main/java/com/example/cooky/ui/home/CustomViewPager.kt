package com.example.cooky.ui.home

import android.content.Context
import android.util.AttributeSet
import android.view.MotionEvent
import androidx.viewpager.widget.ViewPager

class CustomViewPager : ViewPager {

    constructor(context: Context) : super(context)
    constructor(context: Context, attrs: AttributeSet) : super(context, attrs)

    var swipeEnabled = true

    override fun onInterceptTouchEvent(ev: MotionEvent?) =
        if (swipeEnabled) super.onInterceptTouchEvent(ev) else false


    fun setSwipeEnable(enable: Boolean) {
        swipeEnabled = enable
    }
}
