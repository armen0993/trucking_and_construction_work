package com.ml.truckingandconstructionwork.presentation.ui.main.checker_client_or_driver

import android.os.Bundle
import android.view.View
import com.ml.truckingandconstructionwork.R
import com.ml.truckingandconstructionwork.databinding.FragmentCheckerBinding
import com.ml.truckingandconstructionwork.presentation.Constants.CLIENT
import com.ml.truckingandconstructionwork.presentation.Constants.DRIVER
import com.ml.truckingandconstructionwork.presentation.base.BaseFragment

class CheckerFragment : BaseFragment<FragmentCheckerBinding>(FragmentCheckerBinding::inflate) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.toolbar.enableLeftItem(true)
        onClick()

    }

    private fun onClick() {
        with(binding){
            btnClient.setOnClickListener {
                navigateFragment(CheckerFragmentDirections.actionCheckerFragmentToRegistrationFragment(
                    CLIENT))
            }
            btnDriver.setOnClickListener {
                navigateFragment(CheckerFragmentDirections.actionCheckerFragmentToRegistrationFragment(
                    DRIVER))
            }
        }
    }


}