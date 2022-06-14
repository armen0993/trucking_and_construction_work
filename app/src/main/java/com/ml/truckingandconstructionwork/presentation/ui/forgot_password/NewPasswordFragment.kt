package com.ml.truckingandconstructionwork.presentation.ui.forgot_password

import android.os.Bundle
import android.view.View
import androidx.core.widget.doOnTextChanged
import com.ml.truckingandconstructionwork.databinding.FragmentNewPasswordBinding
import com.ml.truckingandconstructionwork.presentation.base.BaseFragment

class NewPasswordFragment : BaseFragment<FragmentNewPasswordBinding>(
    FragmentNewPasswordBinding::inflate
) {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        onClick()
        inputNewAndRepeatPassword()
    }


    private fun onClick() {
        with(binding) {
            btnResetPassword.setOnClickListener {

            }
        }
    }

    private fun checkEmptyFields(): Boolean {
        //TODO Pleas Refactor check logic for text fields
        return binding.password.text?.isNotEmpty() == true && binding.repeatPassword.text?.isNotEmpty() == true
    }

    private fun inputNewAndRepeatPassword() {
        with(binding) {
            password.doOnTextChanged { _, _, _, _ ->
                btnResetPassword.isEnabled = checkEmptyFields()
            }
            repeatPassword.doOnTextChanged { _, _, _, _ ->
                btnResetPassword.isEnabled = checkEmptyFields()
            }
        }
    }
}