package com.ml.truckingandconstructionwork.presentation.ui.settings.language

import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.ml.truckingandconstructionwork.R
import com.ml.truckingandconstructionwork.databinding.AlertDialogChangeLanguageBinding
import com.ml.truckingandconstructionwork.databinding.FragmentLanguageBinding
import com.ml.truckingandconstructionwork.presentation.Constants.ARMENIAN
import com.ml.truckingandconstructionwork.presentation.Constants.ENGLISH
import com.ml.truckingandconstructionwork.presentation.Constants.RUSSIAN
import com.ml.truckingandconstructionwork.presentation.base.BaseFragment
import com.ml.truckingandconstructionwork.presentation.custom_view.EmptyView

class LanguageFragment : BaseFragment<FragmentLanguageBinding>(FragmentLanguageBinding::inflate) {

    private val bindingAlertDialog by lazy { AlertDialogChangeLanguageBinding.inflate(layoutInflater) }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        onClick()
    }

    private fun onClick() {
        with(binding) {
            btnLanguage.setOnClickListener {
                startAlertDialog()
            }
        }
    }

    private fun startAlertDialog() {

        when {
            LocaleHelper.getLanguage(requireContext()) == ENGLISH -> {
                bindingAlertDialog.radioEnglish.isChecked = true
            }
            LocaleHelper.getLanguage(requireContext()) == ARMENIAN -> {
                bindingAlertDialog.radioArmenian.isChecked = true
            }
            LocaleHelper.getLanguage(requireContext()) == RUSSIAN -> {
                bindingAlertDialog.radioRussian.isChecked = true
            }
        }
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
            when(true){
                bindingAlertDialog.radioArmenian.isChecked->
                    LocaleHelper.updateResources(requireContext(), ARMENIAN)
                bindingAlertDialog.radioRussian.isChecked->
                    LocaleHelper.updateResources(requireContext(), RUSSIAN)
                bindingAlertDialog.radioEnglish.isChecked->
                    LocaleHelper.updateResources(requireContext(), ENGLISH)
            }
            dialog.dismiss()
        }

        bindingAlertDialog.dialogAlertCancelButton.setOnClickListener {
            dialog.dismiss()
        }
    }

}