package com.ml.truckingandconstructionwork.presentation.ui.main

import android.os.Bundle
import android.view.Gravity
import android.view.View
import androidx.core.widget.doAfterTextChanged
import androidx.core.widget.doOnTextChanged
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.fragment.findNavController
import com.ml.truckingandconstructionwork.R
import com.ml.truckingandconstructionwork.databinding.FragmentMainBinding
import com.ml.truckingandconstructionwork.presentation.base.BaseFragment

class MainFragment : BaseFragment<FragmentMainBinding>(FragmentMainBinding::inflate) {

    private var emailNotEmpty = false
    private var passwordNotEmpty = false

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.toolbar.enableLeftItem(true)

        openDrawer()
        onClick()
        validation()
        checkerIsEmpty()


    }

    private fun validation() {
        binding.email.doOnTextChanged { text, start, before, count ->
            when {
                binding.email.text.isNullOrEmpty() -> {
                    binding.emailContainer.error = ERROR_EDIT_TEXT
                }
                else -> {
                    binding.emailContainer.error = null
                    emailNotEmpty = true
                }
            }
        }
        binding.email.doAfterTextChanged {
            checkerIsEmpty()
        }
    }

    private fun checkerIsEmpty() {
        binding.btnSignIn.isEnabled = emailNotEmpty && passwordNotEmpty
    }

    private fun onClick() {
        binding.btnSignIn.setOnClickListener {
            when {
                emailNotEmpty && passwordNotEmpty -> {

                    //   navigateFragment(R.id.action_mainFragment_to_registrationFragment)
                }
                !emailNotEmpty -> {
                    binding.emailContainer.error =
                        ERROR_EDIT_TEXT
                }
                !passwordNotEmpty -> {
                    binding.passwordContainer.error =
                        ERROR_EDIT_TEXT
                }

            }

        }

        binding.titleOr.setOnClickListener {
            navigateFragment(R.id.action_mainFragment_to_registrationFragment)
        }
    }

    private fun openDrawer() {
        val drawer = activity?.findViewById<DrawerLayout>(R.id.drawer_layout)
        binding.toolbar.setOnLeftClickListener {
            drawer?.openDrawer(Gravity.LEFT)
        }
    }

    companion object {
        const val ERROR_EDIT_TEXT = "Поле ввода не должно быть пустым"
    }
}