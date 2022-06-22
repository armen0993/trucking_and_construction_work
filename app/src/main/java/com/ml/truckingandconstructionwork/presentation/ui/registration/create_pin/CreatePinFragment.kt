package com.ml.truckingandconstructionwork.presentation.ui.registration.create_pin

import android.os.Bundle
import android.view.View
import com.ml.truckingandconstructionwork.R
import com.ml.truckingandconstructionwork.databinding.FragmentCreatePinBinding
import com.ml.truckingandconstructionwork.presentation.base.BaseFragment
import com.ml.truckingandconstructionwork.presentation.base.BaseViewModel
import com.ml.truckingandconstructionwork.presentation.utils.viewBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class CreatePinFragment() : BaseFragment<BaseViewModel,FragmentCreatePinBinding>() {

    override val binding: FragmentCreatePinBinding by viewBinding()
    override val viewModel: CreatePinViewModel by viewModel()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        onClick()
    }

    private fun onClick(){
        with(binding){
            doNotCreate.setOnClickListener {
                navigateFragment(R.id.action_createPinFragment_to_createFingerprintFragment)
            }
            fingerprintImage.setOnClickListener {
                navigateFragment(R.id.action_createPinFragment_to_createFingerprintFragment)
            }
        }
    }


}