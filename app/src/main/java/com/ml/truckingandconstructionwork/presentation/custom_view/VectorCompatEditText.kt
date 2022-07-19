package com.ml.truckingandconstructionwork.presentation.custom_view

import android.content.Context
import android.graphics.drawable.Drawable
import android.os.Build
import android.util.AttributeSet
import androidx.appcompat.content.res.AppCompatResources
import androidx.appcompat.widget.AppCompatEditText
import com.ml.truckingandconstructionwork.R

open class VectorCompatEditText : AppCompatEditText {
    protected var drawableEnd: Drawable? = null

    constructor(context: Context?) : super(context!!) {}
    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs) {
        initAttrs(context, attrs)
    }

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    ) {
        initAttrs(context, attrs)
    }

    private fun initAttrs(context: Context, attrs: AttributeSet?) {
        if (attrs != null) {
            val attributeArray = context.obtainStyledAttributes(
                attrs,
                R.styleable.VectorCompatEditText
            )
            var drawableStart: Drawable? = null
            var drawableBottom: Drawable? = null
            var drawableTop: Drawable? = null
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                drawableStart =
                    attributeArray.getDrawable(R.styleable.VectorCompatEditText_drawableStartCompat)
                drawableEnd =
                    attributeArray.getDrawable(R.styleable.VectorCompatEditText_drawableEndCompat)
                drawableBottom =
                    attributeArray.getDrawable(R.styleable.VectorCompatEditText_drawableBottomCompat)
                drawableTop =
                    attributeArray.getDrawable(R.styleable.VectorCompatEditText_drawableTopCompat)
            } else {
                val drawableStartId = attributeArray.getResourceId(
                    R.styleable.VectorCompatEditText_drawableStartCompat,
                    -1
                )
                val drawableEndId = attributeArray.getResourceId(
                    R.styleable.VectorCompatEditText_drawableEndCompat,
                    -1
                )
                val drawableBottomId = attributeArray.getResourceId(
                    R.styleable.VectorCompatEditText_drawableBottomCompat,
                    -1
                )
                val drawableTopId = attributeArray.getResourceId(
                    R.styleable.VectorCompatEditText_drawableTopCompat,
                    -1
                )
                if (drawableStartId != -1) drawableStart =
                    AppCompatResources.getDrawable(context, drawableStartId)
                if (drawableEndId != -1) drawableEnd =
                    AppCompatResources.getDrawable(context, drawableEndId)
                if (drawableBottomId != -1) drawableBottom =
                    AppCompatResources.getDrawable(context, drawableBottomId)
                if (drawableTopId != -1) drawableTop =
                    AppCompatResources.getDrawable(context, drawableTopId)
            }
            setCompoundDrawablesRelativeWithIntrinsicBounds(
                drawableStart,
                drawableTop,
                drawableEnd,
                drawableBottom
            )
            attributeArray.recycle()
        }
    }
}
