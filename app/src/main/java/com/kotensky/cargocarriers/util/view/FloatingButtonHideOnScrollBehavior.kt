package com.kotensky.cargocarriers.util.view

import android.content.Context
import android.support.design.widget.CoordinatorLayout
import android.support.design.widget.FloatingActionButton
import android.support.v4.view.ViewCompat
import android.support.v7.widget.RecyclerView
import android.util.AttributeSet
import android.view.View
import android.view.animation.LinearInterpolator

class FloatingButtonHideOnScrollBehavior(context: Context, attrs: AttributeSet) : FloatingActionButton.Behavior(context, attrs) {

    override fun onNestedScroll(coordinatorLayout: CoordinatorLayout, child: FloatingActionButton, target: View, dxConsumed: Int, dyConsumed: Int, dxUnconsumed: Int, dyUnconsumed: Int, type: Int) {
        super.onNestedScroll(coordinatorLayout, child, target, dxConsumed, dyConsumed, dxUnconsumed, dyUnconsumed, type)
        if (target is RecyclerView) {

            if (dyConsumed > 0) {
                val layoutParams = child.layoutParams as CoordinatorLayout.LayoutParams
                val fabBottomMargin = layoutParams.bottomMargin
                child.animate().translationY((child.height + fabBottomMargin).toFloat()).setInterpolator(LinearInterpolator()).start()
            } else if (dyConsumed < 0 && Math.abs(dyConsumed) > 100) {
                child.animate().translationY(0f).setInterpolator(LinearInterpolator()).start()
            } else if (Math.abs(dyUnconsumed) > 0) {
                child.animate().translationY(0f).setInterpolator(LinearInterpolator()).start()
            }
        }
    }

    override fun onStartNestedScroll(coordinatorLayout: CoordinatorLayout, child: FloatingActionButton, directTargetChild: View, target: View, axes: Int): Boolean {
        return axes == ViewCompat.SCROLL_AXIS_VERTICAL
    }

}