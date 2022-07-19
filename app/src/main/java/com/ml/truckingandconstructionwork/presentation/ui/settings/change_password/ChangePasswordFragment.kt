package com.ml.truckingandconstructionwork.presentation.ui.settings.change_password

import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.widget.doOnTextChanged
import androidx.navigation.fragment.navArgs
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.ml.truckingandconstructionwork.R
import com.ml.truckingandconstructionwork.databinding.AlertDialogSuccessBinding
import com.ml.truckingandconstructionwork.databinding.FragmentChangePasswordBinding
import com.ml.truckingandconstructionwork.domain.models.registration.UserDetails
import com.ml.truckingandconstructionwork.presentation.base.BaseFragment
import com.ml.truckingandconstructionwork.presentation.custom_view.EmptyView
import com.ml.truckingandconstructionwork.presentation.utils.viewBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class ChangePasswordFragment :
    BaseFragment<ChangePasswordViewModel, FragmentChangePasswordBinding>() {
    override val viewModel: ChangePasswordViewModel by viewModel()
    override val binding: FragmentChangePasswordBinding by viewBinding()
    private val args: ChangePasswordFragmentArgs by navArgs()
    private val bindingAlertDialog by lazy { AlertDialogSuccessBinding.inflate(layoutInflater) }


    override fun onView() {
        binding.toolbar.enableLeftItem(true)
        inputFields()
    }

    override fun onEach() {
        onEach(viewModel.success) {
            getUser(it)
        }
        onEach(viewModel.showProgressBar) {
            showProgress(it)
        }
    }

    override fun onViewClick() {
        with(binding) {
            btnConfirm.setOnClickListener {
                setPassword()
            }
        }

    }

    private fun setPassword() {
        viewModel.changePassword(
            UserDetails(
                id = args.userId,
                password = binding.oldPassword.text.toString(),
            ), binding.repeatNewPassword.text.toString()
        )
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

        bindingAlertDialog.dialogAlertConfirmButton.setOnClickListener {
            dialog.dismiss()
            popBackStack()
        }
    }

    private fun checkEmptyFields(): Boolean {

        return binding.oldPassword.text?.isNotEmpty() == true &&
                binding.newPassword.text?.isNotEmpty() == true &&
                binding.repeatNewPassword.text?.isNotEmpty() == true
    }

    private fun inputFields() {
        with(binding) {
            oldPassword.doOnTextChanged { _, _, _, _ ->
                btnConfirm.isEnabled = checkEmptyFields()
            }
            newPassword.doOnTextChanged { _, _, _, _ ->
                btnConfirm.isEnabled = checkEmptyFields()
            }
            repeatNewPassword.doOnTextChanged { _, _, _, _ ->
                btnConfirm.isEnabled = checkEmptyFields()
            }
        }
    }

    private fun getUser(boolean: Boolean) {
        if (boolean) {
            startAlertDialog()
        } else {
            Toast.makeText(
                context,
                resources.getString(R.string.please_try_again),
                Toast.LENGTH_SHORT
            ).show()
        }
    }

    private fun showProgress(show: EmptyView.State) {
        binding.emptyView.visibility = View.VISIBLE
        when (show) {
            EmptyView.State.LOADING -> binding.emptyView.showLoader()
            else -> binding.emptyView.hide()
        }
    }
}