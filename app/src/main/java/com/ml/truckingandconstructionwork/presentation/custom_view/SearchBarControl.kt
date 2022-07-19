package com.ml.truckingandconstructionwork.presentation.custom_view

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Rect
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import androidx.core.view.ViewCompat
import androidx.core.widget.doOnTextChanged
import com.ml.truckingandconstructionwork.presentation.extensions.afterTextChanged
import com.ml.truckingandconstructionwork.presentation.listener.SimpleCompoundDrawableClickListener
import kotlinx.coroutines.*
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.flow.debounce

const val DEFAULT_DELAY_MILLS = 500L


class SearchBarControl @JvmOverloads constructor(context: Context, attrs: AttributeSet? = null, defStyle: Int = 0): VectorCompatEditText(context, attrs, defStyle) {
    var clearSearchFieldFunction: (() -> Unit)? = null

    override fun onAttachedToWindow() {
        super.onAttachedToWindow()
        initTouch()
        initClearClickListener()
        showClearIcon(false)
    }

    @SuppressLint("ClickableViewAccessibility")
    private fun initTouch() {
        isClickable = true
        ViewCompat.setFocusedByDefault(this, true)
        isFocusableInTouchMode = true

        (parent as? ViewGroup)?.setOnTouchListener { _, event ->
            if (event.action == MotionEvent.ACTION_DOWN) {

                val outRect = Rect()
                getGlobalVisibleRect(outRect)
                if (!outRect.contains(event.rawX.toInt(), event.rawY.toInt())) {
                    clearFocus()
                }
            }

            false
        }
    }

    private fun initClearClickListener() = setOnTouchListener(drawableClick)

    fun showClearIcon(show: Boolean) {
        if (drawableEnd == null) return
        post {
            val drawables = compoundDrawables
            drawables[2] = null
            if (show) drawables[2] = drawableEnd

            setCompoundDrawablesWithIntrinsicBounds(drawables[0], drawables[1], drawables[2], drawables[3])
        }
    }

    private val drawableClick = object: SimpleCompoundDrawableClickListener() {
        override fun onDrawableClick(v: View?, drawableIndex: Int) {
            if (drawableIndex == RIGHT) {
                text = null
                clearSearchFieldFunction?.invoke()
            }
        }
    }

    @ExperimentalCoroutinesApi
    fun textChange() =
        callbackFlow<String> {
            this@SearchBarControl.doOnTextChanged { text, start, before, count ->
                if(this@SearchBarControl.isFocused && !text.isNullOrEmpty()){
                    trySend(text.toString().trim())
                }
            }.also {
                awaitClose { this@SearchBarControl.removeTextChangedListener(it) }
            }
        }
    fun textChanged()  {
        this.afterTextChanged {
            it.trim()
            showClearIcon(it.isNotEmpty())
        }
    }

    @FlowPreview
    fun debounceTextChange(timeMills: Long = DEFAULT_DELAY_MILLS) = textChange()
        .debounce(timeMills)


}