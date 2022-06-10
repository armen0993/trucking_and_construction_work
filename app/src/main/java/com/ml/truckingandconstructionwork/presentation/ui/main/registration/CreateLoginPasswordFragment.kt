package com.ml.truckingandconstructionwork.presentation.ui.main.registration

import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.ml.truckingandconstructionwork.R
import com.ml.truckingandconstructionwork.databinding.AlertDialogEnterPinBinding
import com.ml.truckingandconstructionwork.databinding.AlertDialogPinOrFingerprintBinding
import com.ml.truckingandconstructionwork.databinding.FragmentCreateLoginPasswordBinding
import com.ml.truckingandconstructionwork.presentation.base.BaseFragment

class CreateLoginPasswordFragment : BaseFragment<FragmentCreateLoginPasswordBinding>(FragmentCreateLoginPasswordBinding::inflate) {

    private val bindingAlertDialog by lazy { AlertDialogPinOrFingerprintBinding.inflate(layoutInflater) }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.toolbar.enableLeftItem(true)

        //Temp
        binding.btnRegistration.isEnabled = true
        onClick()
    }

    private fun onClick(){
        binding.btnRegistration.setOnClickListener {
            startAlertDialog()
        }
    }

    private fun startAlertDialog() {
        if (bindingAlertDialog.root.parent != null) (bindingAlertDialog.root.parent as ViewGroup).removeView(bindingAlertDialog.root)
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