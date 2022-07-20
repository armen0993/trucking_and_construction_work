package com.ml.truckingandconstructionwork.presentation.ui.settings


import android.content.Intent
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.ml.truckingandconstructionwork.MainActivity
import com.ml.truckingandconstructionwork.R
import com.ml.truckingandconstructionwork.databinding.AlertDialogChangeLanguageBinding
import com.ml.truckingandconstructionwork.databinding.AlertDialogExitBinding
import com.ml.truckingandconstructionwork.databinding.FragmentSettingsBinding
import com.ml.truckingandconstructionwork.presentation.utils.Constants
import com.ml.truckingandconstructionwork.presentation.base.BaseFragment
import com.ml.truckingandconstructionwork.presentation.base.BaseViewModel
import com.ml.truckingandconstructionwork.presentation.ui.LocaleHelper
import com.ml.truckingandconstructionwork.presentation.utils.viewBinding
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class SettingsFragment() : BaseFragment<BaseViewModel, FragmentSettingsBinding>() {

    override val binding: FragmentSettingsBinding by viewBinding()
    override val viewModel: SettingsViewModel by viewModel()

    private val bindingAlertDialogLanguage by lazy {
        AlertDialogChangeLanguageBinding.inflate(
            layoutInflater
        )
    }
    private val bindingAlertDialogExit by lazy { AlertDialogExitBinding.inflate(layoutInflater) }
    private var userId = ""

    override fun onView() {
        binding.toolbar.enableLeftItem(true)
        userId = arguments?.getString(Constants.USER_ID)?:""
    }

    override fun onViewClick() {
        with(binding) {
            itemSystemSettings.language.setOnClickListener {
                when {
                    LocaleHelper.getLanguage(requireContext()) == Constants.ENGLISH -> {
                        bindingAlertDialogLanguage.radioEnglish.isChecked = true
                        startAlertDialogLanguage()
                    }
                    LocaleHelper.getLanguage(requireContext()) == Constants.ARMENIAN -> {
                        bindingAlertDialogLanguage.radioArmenian.isChecked = true
                        startAlertDialogLanguage()
                    }
                    LocaleHelper.getLanguage(requireContext()) == Constants.RUSSIAN -> {
                        bindingAlertDialogLanguage.radioRussian.isChecked = true
                        startAlertDialogLanguage()
                    }
                }

            }
            itemExit.btnExit.setOnClickListener {
                if (userId.isNotEmpty()){
                    startAlertDialogExit()
                }else{
                    Toast.makeText(context,getString(R.string.pleace_sign_in), Toast.LENGTH_SHORT).show()
                }
            }
            itemProfileSettings.editProfile.setOnClickListener {
                navigateFragment(
                    SettingsFragmentDirections.actionSettingsFragmentToEditProfileFragment()
                        .setUserId(userId)
                )
            }
            itemProfileSettings.changePassword.setOnClickListener {
                navigateFragment(
                    SettingsFragmentDirections.actionSettingsFragmentToChangePasswordFragment()
                        .setUserId(userId)
                )
            }
        }
    }


    private fun startAlertDialogLanguage() {

        if (bindingAlertDialogLanguage.root.parent != null) (bindingAlertDialogLanguage.root.parent as ViewGroup).removeView(
            bindingAlertDialogLanguage.root
        )
        val dialog = MaterialAlertDialogBuilder(
            requireContext(),
            R.style.ResetTheme
        )
            .setCancelable(false)
            .setView(bindingAlertDialogLanguage.root)
            .show()

        bindingAlertDialogLanguage.dialogAlertConfirmButton.setOnClickListener {
            binding.emptyView.showLoader()
            lifecycleScope.launch {


                when (true) {
                    bindingAlertDialogLanguage.radioArmenian.isChecked ->
                        LocaleHelper.setLocale(requireContext(), Constants.ARMENIAN)
                    bindingAlertDialogLanguage.radioRussian.isChecked ->
                        LocaleHelper.setLocale(requireContext(), Constants.RUSSIAN)
                    bindingAlertDialogLanguage.radioEnglish.isChecked ->
                        LocaleHelper.setLocale(requireContext(), Constants.ENGLISH)
                }
                dialog.dismiss()
                val intent = Intent(context, MainActivity::class.java)
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK)
                delay(1000L)
                binding.emptyView.hide()
//                val bundle = Bundle()
//                bundle.putBoolean("language",true)
//                intent.putExtra("language",bundle)
                startActivity(intent)
            }

        }

        bindingAlertDialogLanguage.dialogAlertCancelButton.setOnClickListener {
            dialog.dismiss()
        }
    }

    private fun startAlertDialogExit() {

        if (bindingAlertDialogExit.root.parent != null) (bindingAlertDialogExit.root.parent as ViewGroup).removeView(
            bindingAlertDialogExit.root
        )
        val dialog = MaterialAlertDialogBuilder(
            requireContext(),
            R.style.ResetTheme
        )
            .setCancelable(false)
            .setView(bindingAlertDialogExit.root)
            .show()

        bindingAlertDialogExit.dialogAlertConfirmButton.setOnClickListener {

            dialog.dismiss()
            lifecycleScope.launch {
                viewModel.signOut()
            }

        }

        bindingAlertDialogExit.dialogAlertCancelButton.setOnClickListener {
            dialog.dismiss()
        }
    }


}