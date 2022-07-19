package com.ml.truckingandconstructionwork.presentation.extensions

import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.EditText

fun View.show(show: Boolean) {
    visibility = if (show) View.VISIBLE else View.GONE
    alpha = 1F
}
fun View.show() {
    visibility =View.VISIBLE
    alpha = 1F
}

fun View.hide() {
    visibility =View.GONE
    alpha = 0F
}

fun View.visible(show: Boolean) {
    visibility = if (show) View.VISIBLE else View.INVISIBLE
}

fun EditText.afterTextChanged(afterTextChanged: (String) -> Unit) {
    this.addTextChangedListener(object : TextWatcher {
        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
        override fun afterTextChanged(editable: Editable?) {
            afterTextChanged.invoke(editable.toString())
        }
    })
}