package com.ml.truckingandconstructionwork.presentation.ui.settings


import android.os.Bundle
import android.view.View
import com.ml.truckingandconstructionwork.databinding.FragmentSettingsBinding
import com.ml.truckingandconstructionwork.presentation.base.BaseFragment

class SettingsFragment : BaseFragment<FragmentSettingsBinding>(FragmentSettingsBinding::inflate) {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        onClick()
    }

    private fun onClick() {
        with(binding) {
            itemSystemSettings.language.setOnClickListener {
                navigateFragment(SettingsFragmentDirections.actionSettingsFragmentToLanguageFragment())
            }
        }
    }


}