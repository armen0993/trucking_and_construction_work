package com.ml.truckingandconstructionwork.presentation.listener

import android.graphics.Rect
import android.view.MotionEvent
import android.view.View

abstract class SimpleCompoundDrawableClickListener : SimpleCompoundDrawableTouchListener {
    constructor() : super() {}
    constructor(indent: Int) : super(indent) {}


    override fun onDrawableTouch(
        v: View?,
        drawableIndex: Int,
        drawableBounds: Rect?,
        event: MotionEvent?
    ): Boolean {
        if (event?.action == MotionEvent.ACTION_UP) onDrawableClick(v, drawableIndex)
        return true    }


    protected abstract fun onDrawableClick(v: View?, @DrawablePosition drawableIndex: Int)
}
