package com.ml.truckingandconstructionwork.presentation.ui.sign_in.registration.checker_client_or_driver

import android.os.Bundle
import android.view.View
import com.ml.truckingandconstructionwork.databinding.FragmentCheckerBinding
import com.ml.truckingandconstructionwork.presentation.utils.Constants.CLIENT
import com.ml.truckingandconstructionwork.presentation.utils.Constants.DRIVER
import com.ml.truckingandconstructionwork.presentation.base.BaseFragment
import com.ml.truckingandconstructionwork.presentation.base.BaseViewModel
import com.ml.truckingandconstructionwork.presentation.utils.viewBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class CheckerFragment() : BaseFragment<BaseViewModel,FragmentCheckerBinding>() {

    override val binding: FragmentCheckerBinding by viewBinding()
    override val viewModel: CheckerViewModel by viewModel()

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