package com.ml.truckingandconstructionwork.presentation.ui.sign_in

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.preference.PreferenceManager
import android.view.LayoutInflater
import android.view.View
import android.view.View.VISIBLE
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.widget.doOnTextChanged
import androidx.navigation.fragment.navArgs
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.ml.truckingandconstructionwork.MainActivity
import com.ml.truckingandconstructionwork.R
import com.ml.truckingandconstructionwork.databinding.AlertDialogPinOrFingerprintBinding
import com.ml.truckingandconstructionwork.databinding.AlertDialogSavePasswordBinding
import com.ml.truckingandconstructionwork.databinding.FragmentSignInBinding
import com.ml.truckingandconstructionwork.presentation.base.BaseFragment
import com.ml.truckingandconstructionwork.presentation.base.BaseViewModel
import com.ml.truckingandconstructionwork.presentation.custom_view.EmptyView
import com.ml.truckingandconstructionwork.presentation.ui.registration.personal_details.PersonalDetailsFragmentDirections
import com.ml.truckingandconstructionwork.presentation.utils.Constants.SPLASH_TYPE
import com.ml.truckingandconstructionwork.presentation.utils.viewBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class SignInFragment() : BaseFragment<BaseViewModel, FragmentSignInBinding>() {

    override val binding: FragmentSignInBinding by viewBinding()
    override val viewModel: SignInViewModel by viewModel()
    private val args: SignInFragmentArgs by navArgs()

    private val bindingAlertDialogSavePassword by lazy {
        AlertDialogSavePasswordBinding.inflate(
            layoutInflater
        )
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        signInType(args.signInType)
        return super.onCreateView(inflater, container, savedInstanceState)
    }

    override fun onView() {
        inputFields()

    }

    override fun onEach() {
        onEach(viewModel.userId) {
            if (it.isNotEmpty()) {
                startAlertDialogSavePassword(it)
            } else {
                Toast.makeText(context, getString(R.string.please_try_again), Toast.LENGTH_SHORT)
                    .show()
            }
        }
        onEach(viewModel.showProgressBar){
            showProgress(it)
        }
    }


    override fun onViewClick() {
        with(binding) {
            btnSignIn.setOnClickListener {
                viewModel.checkUserDetail(
                    binding.email.text.toString(),
                    binding.password.text.toString()
                )
            }
            registerNow.setOnClickListener {
                navigateFragment(SignInFragmentDirections.actionSignInFragmentToRegistrationFragment())
            }
            labelForgotPassword.setOnClickListener {
                navigateFragment(SignInFragmentDirections.actionSignInFragmentToForgotPasswordFragment())
            }
            btnSkip.setOnClickListener {
                navigateFragment(SignInFragmentDirections.actionSignInFragmentToMainFragment())
                viewModel.saveSkippedTypeInSharedPref(true)
            }
        }
    }

    private fun signInType(type: String) {
        when (type) {
            SPLASH_TYPE -> {
                binding.btnSkip.visibility = VISIBLE
            }

        }
    }

    private fun startAlertDialogSavePassword(userId:String) {
        if (bindingAlertDialogSavePassword.root.parent != null) (bindingAlertDialogSavePassword.root.parent as ViewGroup).removeView(
            bindingAlertDialogSavePassword.root
        )
        val dialog = MaterialAlertDialogBuilder(
            requireContext(),
            R.style.ResetTheme
        )
            .setCancelable(false)
            .setView(bindingAlertDialogSavePassword.root)
            .show()

        bindingAlertDialogSavePassword.btnYes.setOnClickListener {
            dialog.dismiss()
            viewModel.saveUserDetailsInSharedPref(userId)

            navigateFragment(SignInFragmentDirections.actionSignInFragmentToProfileFragment().setUserId(userId))
        }
        bindingAlertDialogSavePassword.btnNo.setOnClickListener {
            dialog.dismiss()
            navigateFragment(SignInFragmentDirections.actionSignInFragmentToProfileFragment().setUserId(userId))
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
    private fun showProgress(show: EmptyView.State) {
        binding.emptyView.visibility = VISIBLE
        when(show){
            EmptyView.State.LOADING-> binding.emptyView.showLoader()
            else -> binding.emptyView.hide()
        }

    }
}