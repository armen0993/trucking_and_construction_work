package com.ml.truckingandconstructionwork.presentation.ui.forgot_password.forgot_password

import android.os.Bundle
import android.view.View
import com.ml.truckingandconstructionwork.R
import com.ml.truckingandconstructionwork.databinding.FragmentConstructionWorksBinding
import com.ml.truckingandconstructionwork.databinding.FragmentForgotPasswordBinding
import com.ml.truckingandconstructionwork.presentation.base.BaseFragment
import com.ml.truckingandconstructionwork.presentation.base.BaseViewModel
import com.ml.truckingandconstructionwork.presentation.utils.viewBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class ForgotPasswordFragment() :
    BaseFragment<BaseViewModel,FragmentForgotPasswordBinding>() {
    override val binding: FragmentForgotPasswordBinding by viewBinding()
    override val viewModel: ForgotPasswordViewModel by viewModel()

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