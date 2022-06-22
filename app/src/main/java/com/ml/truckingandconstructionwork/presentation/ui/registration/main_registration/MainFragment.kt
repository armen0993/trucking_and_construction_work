package com.ml.truckingandconstructionwork.presentation.ui.registration.main_registration

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

class MainFragment() : BaseFragment<BaseViewModel,FragmentMainBinding>() {

    override val binding: FragmentMainBinding by viewBinding()
    override val viewModel: MainViewModel by viewModel()
    private var emailNotEmpty = false
    private var passwordNotEmpty = false

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //Temp
        binding.btnSignIn.isEnabled = true

        onClick()
        validation()
        checkerIsEmpty()
        initButtonClickListener()
    }

    private fun validation() {
        binding.email.doOnTextChanged { text, start, before, count ->
            when {
                binding.email.text.isNullOrEmpty() -> {
                    binding.emailContainer.error = ERROR_EDIT_TEXT
                }
                else -> {
                    binding.emailContainer.error = null
                    emailNotEmpty = true
                }
            }
        }
        binding.email.doAfterTextChanged {
            checkerIsEmpty()
        }
    }

    private fun checkerIsEmpty() {
        binding.btnSignIn.isEnabled = emailNotEmpty && passwordNotEmpty
    }

    private fun onClick() {
        with(binding) {
            btnSignIn.setOnClickListener {
                when {
                    emailNotEmpty && passwordNotEmpty -> {

                        //   navigateFragment(R.id.action_mainFragment_to_registrationFragment)
                    }
                    !emailNotEmpty -> {
                        binding.emailContainer.error =
                            ERROR_EDIT_TEXT
                    }
                    !passwordNotEmpty -> {
                        binding.passwordContainer.error =
                            ERROR_EDIT_TEXT
                    }

                }

            }
            registerNow.setOnClickListener {
                navigateFragment(R.id.action_mainFragment_to_checkerFragment)
            }
            btnSignIn.setOnClickListener {
                navigateFragment(R.id.action_mainFragment_to_profileFragment)
            }
        }


    }


    private fun goToForgotPassword() {

        navigateFragment(R.id.action_mainFragment_to_forgotPasswordFragment)
    }


    private fun initButtonClickListener() {
        binding.labelForgotPassword.setOnClickListener {
            goToForgotPassword()
        }
    }

    companion object {
        const val ERROR_EDIT_TEXT = "Поле ввода не должно быть пустым"
    }
}