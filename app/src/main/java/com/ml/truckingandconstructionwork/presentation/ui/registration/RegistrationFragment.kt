package com.ml.truckingandconstructionwork.presentation.ui.registration

import android.widget.Toast
import androidx.core.widget.doOnTextChanged
import com.ml.truckingandconstructionwork.databinding.FragmentRegistrationBinding
import com.ml.truckingandconstructionwork.domain.models.registration.UserDetails
import com.ml.truckingandconstructionwork.presentation.base.BaseFragment
import com.ml.truckingandconstructionwork.presentation.base.BaseViewModel
import com.ml.truckingandconstructionwork.presentation.extensions.GENERATE_ID
import com.ml.truckingandconstructionwork.presentation.utils.viewBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class RegistrationFragment() :
    BaseFragment<BaseViewModel, FragmentRegistrationBinding>() {

    override val binding: FragmentRegistrationBinding by viewBinding()
    override val viewModel: RegistrationViewModel by viewModel()
    private var id= ""

    override fun onView() {
        inputFields()
    }

    override fun onViewClick() {
        binding.btnRegistration.setOnClickListener {
            userDetails()
        }
    }

    override fun onEach() {
        onEach(viewModel.startAlertDialog){
            if (it) {
              navigateFragment(RegistrationFragmentDirections.actionCreateLoginPasswordFragmentToCheckerFragment(id))
            } else {
                Toast.makeText(context, "Please check your data", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun checkEmptyFields(): Boolean {
        return binding.email.text?.isNotEmpty() == true &&
                binding.password.text?.isNotEmpty() == true &&
                binding.repeatPassword.text?.isNotEmpty() == true

    }

    private fun inputFields() {
        with(binding) {
            email.doOnTextChanged { _, _, _, _ ->
                btnRegistration.isEnabled = checkEmptyFields()
            }
            password.doOnTextChanged { _, _, _, _ ->
                btnRegistration.isEnabled = checkEmptyFields()
            }
            repeatPassword.doOnTextChanged { _, _, _, _ ->
                btnRegistration.isEnabled = checkEmptyFields()
            }
        }
    }

    private fun userDetails() {
        id = GENERATE_ID
        viewModel.setUserDetails(
            UserDetails(
                id = id,
                email = binding.email.text.toString(),
                password = binding.repeatPassword.text.toString()
            )
        )
    }

    private fun showProgress(show: Boolean) {
        if (show) binding.emptyView.showLoader() else binding.emptyView.hide()
    }
}