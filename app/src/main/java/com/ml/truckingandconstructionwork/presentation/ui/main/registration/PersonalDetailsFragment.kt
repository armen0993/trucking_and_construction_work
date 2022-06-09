package com.ml.truckingandconstructionwork.presentation.ui.main.registration

import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.ml.truckingandconstructionwork.R
import com.ml.truckingandconstructionwork.databinding.AlertDialogEnterPinBinding
import com.ml.truckingandconstructionwork.databinding.FragmentPersonalDetailsBinding
import com.ml.truckingandconstructionwork.presentation.base.BaseFragment


class PersonalDetailsFragment : BaseFragment<FragmentPersonalDetailsBinding>(
    FragmentPersonalDetailsBinding::inflate
) {

    private val bindingAlertDialog by lazy { AlertDialogEnterPinBinding.inflate(layoutInflater) }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.toolbar.enableLeftItem(true)

        //Temp
        binding.btnNext.isEnabled = true

        onClick()
    }

    private fun onClick() {
        binding.btnNext.setOnClickListener {
            startAlertDialog()

        }
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
            navigateFragment(R.id.action_personalDetailsFragment_to_createLoginPasswordFragment)
        }
        bindingAlertDialog.dialogAlertCancelButton.setOnClickListener {
            dialog.dismiss()
        }
    }

}