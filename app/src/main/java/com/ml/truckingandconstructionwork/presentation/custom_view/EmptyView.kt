package com.ml.truckingandconstructionwork.presentation.custom_view

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import androidx.constraintlayout.widget.ConstraintLayout
import com.ml.truckingandconstructionwork.R
import com.ml.truckingandconstructionwork.databinding.ViewEmptyBinding

class EmptyView(context: Context, attrs: AttributeSet) : ConstraintLayout(context, attrs) {
    private var _binding: ViewEmptyBinding? = null
    val binding get() = _binding!!

    private var emptyMessage: String?
    private var loadingMessage: String?

    init {
        _binding = ViewEmptyBinding.inflate(LayoutInflater.from(context), this, true)

        // Default configuration
        binding.imageView.setImageResource(R.drawable.ic_empty)
        emptyMessage = context.getString(R.string.empty_view_no_data)
        loadingMessage = context.getString(R.string.empty_view_loading)

        setBackgroundResource(R.color.ptGray4)
    }

    fun setState(state: State) {
        binding.apply {
            when(state) {
                State.EMPTY -> {
                    messageTextView.text = emptyMessage

                    progressBar.visibility = View.INVISIBLE
                    imageView.visibility = View.VISIBLE
                    messageTextView.visibility = View.VISIBLE

                    visibility = View.VISIBLE
                }
                State.LOADING -> {
                    messageTextView.text = loadingMessage

                    progressBar.visibility = View.VISIBLE
                    imageView.visibility = View.INVISIBLE
                    messageTextView.visibility = View.VISIBLE

                    visibility = View.VISIBLE
                }
                State.HIDE -> {

                    progressBar.visibility = View.INVISIBLE
                    imageView.visibility = View.INVISIBLE
                    messageTextView.visibility = View.GONE
                    visibility = View.GONE
                }
            }
        }
    }

    fun showLoader() = setState(State.LOADING)
    fun showEmpty() = setState(State.EMPTY)
    fun hide() = setState(State.HIDE)

    enum class State {
        EMPTY, LOADING, HIDE
    }
}