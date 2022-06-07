package com.ml.truckingandconstructionwork.presentation.ui.main.registration

import android.os.Bundle
import android.view.View
import com.ml.truckingandconstructionwork.databinding.FragmentRegistrationBinding
import com.ml.truckingandconstructionwork.presentation.base.BaseFragment

class RegistrationFragment : BaseFragment<FragmentRegistrationBinding>(FragmentRegistrationBinding::inflate) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.toolbar.enableLeftItem(true)

    }

}