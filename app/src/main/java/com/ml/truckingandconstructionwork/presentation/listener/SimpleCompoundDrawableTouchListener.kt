package com.ml.truckingandconstructionwork.presentation.listener

import android.graphics.Rect
import android.graphics.drawable.Drawable
import android.view.MotionEvent
import android.view.View
import android.widget.TextView
import androidx.annotation.IntDef
import java.lang.annotation.Retention
import java.lang.annotation.RetentionPolicy

abstract class SimpleCompoundDrawableTouchListener @JvmOverloads constructor(private val indent: Int = 0) :
    View.OnTouchListener {
    @IntDef(LEFT, TOP, RIGHT, BOTTOM)
    @Retention(RetentionPolicy.SOURCE)
    annotation class DrawablePosition

    override fun onTouch(view: View, event: MotionEvent): Boolean {
        if (view !is TextView) {
//            LogUtils.logError(TAG, "attached view is not instance of TextView")
            return false
        }
        val textView = view
        val drawables = textView.compoundDrawables
        val x = event.x.toInt()
        val y = event.y.toInt()
        for (i in DRAWABLE_INDEXES) {
            if (drawables[i] == null) continue
            val bounds = getRelativeBounds(i, drawables[i], textView)
            val indentedBounds = addIndent(bounds)
            if (indentedBounds.contains(x, y)) {
                val relativeEvent = MotionEvent.obtain(
                    event.downTime,
                    event.eventTime,
                    event.action,
                    event.x - bounds.left,
                    event.y - bounds.top,
                    event.metaState
                )
                return onDrawableTouch(view, i, bounds, relativeEvent)
            }
        }
        return false
    }

    private fun getRelativeBounds(
        @DrawablePosition index: Int,
        drawable: Drawable,
        view: View
    ): Rect {
        val drawableBounds = drawable.bounds
        val bounds = Rect(drawableBounds)
        when (index) {
            LEFT -> bounds.offsetTo(
                view.paddingLeft,
                view.height / 2 - bounds.height() / 2
            )
            TOP -> bounds.offsetTo(
                view.width / 2 - bounds.width() / 2,
                view.paddingTop
            )
            RIGHT -> bounds.offsetTo(
                view.width - view.paddingRight - bounds.width(),
                view.height / 2 - bounds.height() / 2
            )
            BOTTOM -> bounds.offsetTo(
                view.width / 2 - bounds.width() / 2,
                view.height - view.paddingBottom - bounds.height()
            )
        }
        return bounds
    }

    private fun addIndent(source: Rect): Rect {
        val result = Rect()
        result.left = source.left - indent
        result.right = source.right + indent
        result.top = source.top - indent
        result.bottom = source.bottom + indent
        return result
    }

    protected abstract fun onDrawableTouch(
        v: View?,
        @DrawablePosition drawableIndex: Int,
        drawableBounds: Rect?,
        event: MotionEvent?
    ): Boolean

    companion object {
        val TAG = SimpleCompoundDrawableTouchListener::class.java.simpleName
        const val LEFT = 0
        const val TOP = 1
        const val RIGHT = 2
        const val BOTTOM = 3
        private val DRAWABLE_INDEXES = intArrayOf(LEFT, TOP, RIGHT, BOTTOM)
    }
}
