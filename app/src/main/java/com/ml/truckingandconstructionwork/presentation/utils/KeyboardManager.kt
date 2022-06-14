package com.ml.truckingandconstructionwork.presentation.utils

import android.content.Context
import android.view.View
import android.view.inputmethod.InputMethodManager

fun hideKeyboard(context: Context, view: View?) {
    val inputMethodeManager =
        context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
    inputMethodeManager.hideSoftInputFromWindow(view?.windowToken, 0)
}

fun showKeyboard(context: Context) {
    val inputMethodeManager =
        context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
    inputMethodeManager.toggleSoftInput(InputMethodManager.SHOW_FORCED, 0)
}