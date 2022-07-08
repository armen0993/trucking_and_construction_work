package com.ml.truckingandconstructionwork.presentation.ui.sign_in

import android.content.SharedPreferences
import android.preference.PreferenceManager
import androidx.core.widget.doOnTextChanged
import com.ml.truckingandconstructionwork.databinding.FragmentSignInBinding
import com.ml.truckingandconstructionwork.presentation.base.BaseFragment
import com.ml.truckingandconstructionwork.presentation.base.BaseViewModel
import com.ml.truckingandconstructionwork.presentation.utils.viewBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class SignInFragment() : BaseFragment<BaseViewModel, FragmentSignInBinding>() {

    override val binding: FragmentSignInBinding by viewBinding()
    override val viewModel: SignInViewModel by viewModel()

    private lateinit var sharedPref: SharedPreferences

    override fun onView() {
        sharedPref = PreferenceManager.getDefaultSharedPreferences(context)
        inputFields()
    }

    override fun onViewClick() {
        with(binding) {
            btnSignIn.setOnClickListener {
                navigateFragment(SignInFragmentDirections.actionSignInFragmentToProfileFragment())
            }
            registerNow.setOnClickListener {
                navigateFragment(SignInFragmentDirections.actionSignInFragmentToRegistrationFragment())
            }
            labelForgotPassword.setOnClickListener {
                navigateFragment(SignInFragmentDirections.actionSignInFragmentToForgotPasswordFragment())
            }
            btnSkip.setOnClickListener {
                navigateFragment(SignInFragmentDirections.actionSignInFragmentToProfileFragment())
                sharedPref.edit().putBoolean(SKIPED, true).apply()
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
        const val SKIPED = "skiped"
    }
}