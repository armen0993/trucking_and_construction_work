package com.ml.truckingandconstructionwork.presentation.ui.sign_in

import android.content.SharedPreferences
import android.os.Bundle
import android.preference.PreferenceManager
import android.view.LayoutInflater
import android.view.View
import android.view.View.VISIBLE
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.widget.doOnTextChanged
import androidx.navigation.fragment.navArgs
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.ml.truckingandconstructionwork.MainActivity
import com.ml.truckingandconstructionwork.R
import com.ml.truckingandconstructionwork.databinding.AlertDialogPinOrFingerprintBinding
import com.ml.truckingandconstructionwork.databinding.AlertDialogSavePasswordBinding
import com.ml.truckingandconstructionwork.databinding.FragmentSignInBinding
import com.ml.truckingandconstructionwork.presentation.base.BaseFragment
import com.ml.truckingandconstructionwork.presentation.base.BaseViewModel
import com.ml.truckingandconstructionwork.presentation.ui.registration.personal_details.PersonalDetailsFragmentDirections
import com.ml.truckingandconstructionwork.presentation.utils.Constants.SPLASH_TYPE
import com.ml.truckingandconstructionwork.presentation.utils.viewBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class SignInFragment() : BaseFragment<BaseViewModel, FragmentSignInBinding>() {

    override val binding: FragmentSignInBinding by viewBinding()
    override val viewModel: SignInViewModel by viewModel()
    private val args: SignInFragmentArgs by navArgs()

    private val bindingAlertDialogSavePassword by lazy {
        AlertDialogSavePasswordBinding.inflate(
            layoutInflater
        )
    }

    private lateinit var sharedPref: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        signInType(args.signInType)
        return super.onCreateView(inflater, container, savedInstanceState)
    }

    override fun onView() {
        sharedPref = PreferenceManager.getDefaultSharedPreferences(context)
        inputFields()

    }

    override fun onEach() {
        onEach(viewModel.userId) {
            if (it.isNotEmpty()) {
                startAlertDialogSavePassword(it)
            } else {
                Toast.makeText(context, getString(R.string.please_try_again), Toast.LENGTH_SHORT)
                    .show()
            }
        }
    }


    override fun onViewClick() {
        with(binding) {
            btnSignIn.setOnClickListener {
                viewModel.checkUserDetail(
                    binding.email.text.toString(),
                    binding.password.text.toString()
                )
                // navigateFragment(SignInFragmentDirections.actionSignInFragmentToProfileFragment())
            }
            registerNow.setOnClickListener {
                navigateFragment(SignInFragmentDirections.actionSignInFragmentToRegistrationFragment())
            }
            labelForgotPassword.setOnClickListener {
                navigateFragment(SignInFragmentDirections.actionSignInFragmentToForgotPasswordFragment())
            }
            btnSkip.setOnClickListener {
                navigateFragment(SignInFragmentDirections.actionSignInFragmentToMainFragment())
                sharedPref.edit().putBoolean(SKIPED, true).apply()
            }
        }
    }

    private fun signInType(type: String) {
        when (type) {
            SPLASH_TYPE -> {
                binding.btnSkip.visibility = VISIBLE
            }

        }
    }

    private fun startAlertDialogSavePassword(userId:String) {
        if (bindingAlertDialogSavePassword.root.parent != null) (bindingAlertDialogSavePassword.root.parent as ViewGroup).removeView(
            bindingAlertDialogSavePassword.root
        )
        val dialog = MaterialAlertDialogBuilder(
            requireContext(),
            R.style.ResetTheme
        )
            .setCancelable(false)
            .setView(bindingAlertDialogSavePassword.root)
            .show()

        bindingAlertDialogSavePassword.btnYes.setOnClickListener {
            dialog.dismiss()
            viewModel.saveUserDetailsInSharedPref(userId)
            if (userId.isNotEmpty()){

            }
            navigateFragment(SignInFragmentDirections.actionSignInFragmentToProfileFragment(userId))
        }
        bindingAlertDialogSavePassword.btnNo.setOnClickListener {
            dialog.dismiss()
            navigateFragment(SignInFragmentDirections.actionSignInFragmentToProfileFragment(userId))
        }
    }

    private fun checkEmptyFields(): Boolean {

        return binding.email.text?.isNotEmpty() == true &&
                binding.password.text?.isNotEmpty() == true

    }

    private fun inputFields() {
        with(binding) {
            email.doOnTextChanged { _, _, _, _ ->
                btnSignIn.isEnabled = checkEmptyFields()
            }
            password.doOnTextChanged { _, _, _, _ ->
                btnSignIn.isEnabled = checkEmptyFields()
            }
        }
    }

    companion object {
        const val ERROR_EDIT_TEXT = "Поле ввода не должно быть пустым"
        const val SKIPED = "skiped"
    }
}