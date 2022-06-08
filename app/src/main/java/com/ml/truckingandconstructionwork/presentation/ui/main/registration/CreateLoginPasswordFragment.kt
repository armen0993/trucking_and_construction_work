package com.ml.truckingandconstructionwork.presentation.ui.main.registration

import android.os.Bundle
import android.view.View
import com.ml.truckingandconstructionwork.databinding.FragmentCreateLoginPasswordBinding
import com.ml.truckingandconstructionwork.presentation.base.BaseFragment

class CreateLoginPasswordFragment : BaseFragment<FragmentCreateLoginPasswordBinding>(FragmentCreateLoginPasswordBinding::inflate) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.toolbar.enableLeftItem(true)
    }

}