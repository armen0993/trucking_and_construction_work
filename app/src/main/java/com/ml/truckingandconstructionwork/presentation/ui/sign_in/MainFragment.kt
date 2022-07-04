package com.ml.truckingandconstructionwork.presentation.ui.sign_in

import android.os.Bundle
import android.view.View
import androidx.core.widget.doAfterTextChanged
import androidx.core.widget.doOnTextChanged
import com.ml.truckingandconstructionwork.R
import com.ml.truckingandconstructionwork.databinding.FragmentMainBinding
import com.ml.truckingandconstructionwork.presentation.base.BaseFragment
import com.ml.truckingandconstructionwork.presentation.base.BaseViewModel
import com.ml.truckingandconstructionwork.presentation.utils.viewBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainFragment() : BaseFragment<BaseViewModel, FragmentMainBinding>() {

    override val binding: FragmentMainBinding by viewBinding()
    override val viewModel: MainViewModel by viewModel()

    override fun onView() {
        inputFields()
    }

    override fun onViewClick() {
        with(binding) {
            btnSignIn.setOnClickListener {
                navigateFragment(MainFragmentDirections.actionMainFragmentToProfileFragment())
            }
            registerNow.setOnClickListener {
                navigateFragment(MainFragmentDirections.actionMainFragmentToCheckerFragment())
            }
            labelForgotPassword.setOnClickListener {
                navigateFragment(MainFragmentDirections.actionMainFragmentToForgotPasswordFragment())
            }
        }
    }

    private fun checkEmptyFields(): Boolean {

        return binding.email.text?.isNotEmpty() == true &&
                binding.password.text?.isNotEmpty() == true

    }

    private fun inputFields() {
        with(binding) {
            email.doOnTextChanged { _, _, _, _ ->
                btnSignIn.isEnabled = checkEmptyFields()
            }
            password.doOnTextChanged { _, _, _, _ ->
                btnSignIn.isEnabled = checkEmptyFields()
            }
        }
    }

    companion object {
        const val ERROR_EDIT_TEXT = "Поле ввода не должно быть пустым"
    }
}