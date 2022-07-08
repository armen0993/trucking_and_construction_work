package com.ml.truckingandconstructionwork.presentation.ui.registration

import android.view.ViewGroup
import android.widget.Toast
import androidx.core.widget.doOnTextChanged
import androidx.lifecycle.lifecycleScope
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.ml.truckingandconstructionwork.R
import com.ml.truckingandconstructionwork.databinding.AlertDialogPinOrFingerprintBinding
import com.ml.truckingandconstructionwork.databinding.FragmentRegistrationBinding
import com.ml.truckingandconstructionwork.domain.models.registration.UserDetails
import com.ml.truckingandconstructionwork.presentation.base.BaseFragment
import com.ml.truckingandconstructionwork.presentation.base.BaseViewModel
import com.ml.truckingandconstructionwork.presentation.utils.viewBinding
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class RegistrationFragment() :
    BaseFragment<BaseViewModel, FragmentRegistrationBinding>() {

    override val binding: FragmentRegistrationBinding by viewBinding()
    override val viewModel: RegistrationViewModel by viewModel()

    private val bindingAlertDialog by lazy {
        AlertDialogPinOrFingerprintBinding.inflate(
            layoutInflater
        )
    }


    override fun onView() {

        inputFields()



    }

    override fun onViewClick() {
        binding.btnRegistration.setOnClickListener {
            userDetails()

        }

    }

    override fun onEach() {
//        onEach(viewModel.showProgressBar) {
//            showProgress(it)
//        }
//        onEach(viewModel.startAlertDialog) {
//            if (it) {
//                startAlertDialog()
//            } else {
//                Toast.makeText(context, "Please check your data", Toast.LENGTH_SHORT).show()
//            }
//        }


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
                login = binding.login.text.toString(),
                password = binding.repeatPassword.text.toString()
            )
        )

        lifecycleScope.launch{
            viewModel.showProgressBar.collect {
                showProgress(it)
            }
            viewModel.startAlertDialog.collect {
                if (it) {
                    startAlertDialog()
                } else {
                    Toast.makeText(context, "Please check your data", Toast.LENGTH_SHORT).show()
                }
            }
        }
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
           // navigateFragment(R.id.action_createLoginPasswordFragment_to_createPinFragment)
        }
        bindingAlertDialog.btnUseFingerprint.setOnClickListener {
            dialog.dismiss()
           // navigateFragment(R.id.action_createLoginPasswordFragment_to_createFingerprintFragment)
        }
        bindingAlertDialog.dialogAlertCancelButton.setOnClickListener {
            dialog.dismiss()
            navigateFragment(R.id.action_createLoginPasswordFragment_to_profileFragment)
        }
    }

}