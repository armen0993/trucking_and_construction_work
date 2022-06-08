package com.ml.truckingandconstructionwork.presentation.ui.main.registration

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.ml.truckingandconstructionwork.R
import com.ml.truckingandconstructionwork.databinding.DialogAlertBinding
import com.ml.truckingandconstructionwork.databinding.FragmentPersonalDetailsBinding
import com.ml.truckingandconstructionwork.presentation.base.BaseFragment

class PersonalDetailsFragment : BaseFragment<FragmentPersonalDetailsBinding>(
    FragmentPersonalDetailsBinding::inflate
) {

    private val bindingAlertDialog by lazy { DialogAlertBinding.inflate(layoutInflater) }

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
        val dialog = MaterialAlertDialogBuilder(
            requireContext(),
            R.style.ResetTheme
        )
            .setView(bindingAlertDialog.root)
            .setCancelable(false)
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