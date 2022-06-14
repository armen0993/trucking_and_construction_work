package com.ml.truckingandconstructionwork.presentation.ui.forgot_password

import android.os.Bundle
import android.view.View
import com.ml.truckingandconstructionwork.R
import com.ml.truckingandconstructionwork.databinding.FragmentForgotPasswordBinding
import com.ml.truckingandconstructionwork.presentation.base.BaseFragment

class ForgotPasswordFragment :
    BaseFragment<FragmentForgotPasswordBinding>(FragmentForgotPasswordBinding::inflate) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        onClick()
    }


    private fun onClick() {
        with(binding) {
            btnSendEmail.setOnClickListener {
                navigateFragment(R.id.action_forgotPasswordFragment_to_verificationFragment)
            }
        }
    }
}