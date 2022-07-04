package com.ml.truckingandconstructionwork.presentation.ui.sign_in.forgot_password.new_password

import android.os.Bundle
import android.view.View
import androidx.core.widget.doOnTextChanged
import com.ml.truckingandconstructionwork.databinding.FragmentNewPasswordBinding
import com.ml.truckingandconstructionwork.presentation.base.BaseFragment
import com.ml.truckingandconstructionwork.presentation.base.BaseViewModel
import com.ml.truckingandconstructionwork.presentation.utils.viewBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class NewPasswordFragment() : BaseFragment<BaseViewModel,FragmentNewPasswordBinding>(

) {

    override val binding: FragmentNewPasswordBinding by viewBinding()
    override val viewModel: NewPasswordViewModel by viewModel()

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