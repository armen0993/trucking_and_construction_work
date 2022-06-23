package com.ml.truckingandconstructionwork.presentation.ui.registration.create_login_password

import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.widget.doOnTextChanged
import androidx.navigation.fragment.navArgs
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.ml.truckingandconstructionwork.R
import com.ml.truckingandconstructionwork.databinding.AlertDialogPinOrFingerprintBinding
import com.ml.truckingandconstructionwork.databinding.FragmentCreateLoginPasswordBinding
import com.ml.truckingandconstructionwork.domain.models.UserDetails
import com.ml.truckingandconstructionwork.presentation.base.BaseFragment
import com.ml.truckingandconstructionwork.presentation.base.BaseViewModel
import com.ml.truckingandconstructionwork.presentation.utils.viewBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class CreateLoginPasswordFragment() :
    BaseFragment<BaseViewModel, FragmentCreateLoginPasswordBinding>() {

    override val binding: FragmentCreateLoginPasswordBinding by viewBinding()
    override val viewModel: CreateLoginPasswordViewModel by viewModel()

    private val bindingAlertDialog by lazy {
        AlertDialogPinOrFingerprintBinding.inflate(
            layoutInflater
        )
    }
    private val args:CreateLoginPasswordFragmentArgs by navArgs()

    override fun onView() {
        binding.toolbar.enableLeftItem(true)
        inputFields()

    }

    override fun onViewClick() {
        binding.btnRegistration.setOnClickListener {
            userDetails()

        }

    }

    override fun onEach() {
        onEach(viewModel.showProgressBar) {
            showProgress(it)
        }
        onEach(viewModel.startAlertDialog) {
            if (it) {
                startAlertDialog()
            } else {
                Toast.makeText(context, "Please check your data", Toast.LENGTH_SHORT).show()
            }
        }
    }


    private fun checkEmptyFields(): Boolean {

        return binding.login.text?.isNotEmpty() == true &&
                binding.password.text?.isNotEmpty() == true &&
                binding.repeatPassword.text?.isNotEmpty() == true

    }

    private fun inputFields() {
        with(binding) {
            login.doOnTextChanged { _, _, _, _ ->
                btnRegistration.isEnabled = checkEmptyFields()
            }
            password.doOnTextChanged { _, _, _, _ ->
                btnRegistration.isEnabled = checkEmptyFields()
            }
            repeatPassword.doOnTextChanged { _, _, _, _ ->
                btnRegistration.isEnabled = checkEmptyFields()
            }
        }
    }

    private fun userDetails() {
        viewModel.setUserDetails(
            UserDetails(
                userid = args.userId,
                login = binding.login.text.toString(),
                password = binding.repeatPassword.text.toString()
            )
        )
    }

    private fun showProgress(show: Boolean) {
        if (show) binding.emptyView.showLoader() else binding.emptyView.hide()
    }

    private fun startAlertDialog() {
        if (bindingAlertDialog.root.parent != null) (bindingAlertDialog.root.parent as ViewGroup).removeView(
            bindingAlertDialog.root
        )
        val dialog = MaterialAlertDialogBuilder(
            requireContext(),
            R.style.ResetTheme
        )
            .setCancelable(false)
            .setView(bindingAlertDialog.root)
            .show()

        bindingAlertDialog.btnUsePin.setOnClickListener {
            dialog.dismiss()
            navigateFragment(R.id.action_createLoginPasswordFragment_to_createPinFragment)
        }
        bindingAlertDialog.btnUseFingerprint.setOnClickListener {
            dialog.dismiss()
            navigateFragment(R.id.action_createLoginPasswordFragment_to_createFingerprintFragment)
        }
        bindingAlertDialog.dialogAlertCancelButton.setOnClickListener {
            dialog.dismiss()
            navigateFragment(R.id.action_createLoginPasswordFragment_to_profileFragment)
        }
    }

}