package com.ml.truckingandconstructionwork.presentation.ui.forgot_password

import android.os.Bundle
import android.view.View
import androidx.core.widget.doOnTextChanged
import com.google.android.material.snackbar.Snackbar
import com.ml.truckingandconstructionwork.R
import com.ml.truckingandconstructionwork.databinding.FragmentVerificationForgotPasswordBinding
import com.ml.truckingandconstructionwork.presentation.base.BaseFragment
import com.ml.truckingandconstructionwork.presentation.utils.hideKeyboard
import com.ml.truckingandconstructionwork.presentation.utils.showKeyboard

class VerificationForgotPasswordFragment :
    BaseFragment<FragmentVerificationForgotPasswordBinding>(
        FragmentVerificationForgotPasswordBinding::inflate
    ) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        onClick()
        initView(view = view)
        inputOtp()
    }


    private fun onClick() {
        with(binding) {
            btnVerify.setOnClickListener {
                navigateFragment(R.id.action_verificationFragment_to_newPasswordFragment)
            }
            btnResendOtp.setOnClickListener {
                Snackbar.make(binding.root, "Resent btn Clicked", Snackbar.LENGTH_SHORT).show()
                //TODO Please Handle This click
            }
        }
    }

    private fun initView(view: View) {
        with(binding) {
            editVerificationCode.apply {
                requestFocus()
                setOnFocusChangeListener { _, hasFocus ->
                    if (hasFocus) {
                        showKeyboard(requireContext())
                    } else {
                        hideKeyboard(requireContext(), view)
                    }
                }
            }
        }
    }

    private fun inputOtp() {
        binding.editVerificationCode.doOnTextChanged { text, _, _, _ ->
            val oldCode = text.toString()
            when (text?.length) {
                0 -> binding.textInputCodeOne.text = ""
                1 -> {
                    with(binding) {
                        textInputCodeOne.text = text[oldCode.length - 1].toString()
                        textInputCodeTwo.text = ""
                    }
                }
                2 -> {
                    with(binding) {
                        textInputCodeTwo.text = text[oldCode.length - 1].toString()
                        textInputCodeThree.text = ""
                    }
                }
                3 -> {
                    with(binding) {
                        textInputCodeThree.text = text[oldCode.length - 1].toString()
                        textInputCodeFour.text = ""
                    }
                }
                4 -> {
                    with(binding) {
                        textInputCodeFour.text = text[oldCode.length - 1].toString()
                    }
                }

            }
        }
    }
}