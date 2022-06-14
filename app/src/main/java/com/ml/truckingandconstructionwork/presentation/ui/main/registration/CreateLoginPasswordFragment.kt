package com.ml.truckingandconstructionwork.presentation.ui.main.registration

import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.ml.truckingandconstructionwork.R
import com.ml.truckingandconstructionwork.databinding.AlertDialogEnterPinBinding
import com.ml.truckingandconstructionwork.databinding.AlertDialogPinOrFingerprintBinding
import com.ml.truckingandconstructionwork.databinding.FragmentCreateLoginPasswordBinding
import com.ml.truckingandconstructionwork.presentation.Constants.DOC_LOGIN_PASSWORD
import com.ml.truckingandconstructionwork.presentation.Constants.USER_DETAILS
import com.ml.truckingandconstructionwork.presentation.base.BaseFragment
import com.ml.truckingandconstructionwork.presentation.models.UserDetails
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class CreateLoginPasswordFragment :
    BaseFragment<FragmentCreateLoginPasswordBinding>(FragmentCreateLoginPasswordBinding::inflate) {

    private val bindingAlertDialog by lazy {
        AlertDialogPinOrFingerprintBinding.inflate(
            layoutInflater
        )
    }
    private val db = Firebase.firestore

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.toolbar.enableLeftItem(true)

        //Temp
        binding.btnRegistration.isEnabled = true
        onClick()
    }

    private fun onClick() {
        binding.btnRegistration.setOnClickListener {
            userDetails()

        }
    }

    private fun userDetails() {
        binding.emptyView.showLoader()
        db.collection(USER_DETAILS).document(DOC_LOGIN_PASSWORD)
            .set(
                UserDetails(
                    login = binding.login.text.toString(),
                    password = binding.repeatPassword.text.toString()
                )
            )
            .addOnSuccessListener {

                lifecycleScope.launch {
                    delay(1000L)
                    binding.emptyView.hide()
                    startAlertDialog()
                }


            }
            .addOnFailureListener {
                Toast.makeText(
                    context,
                    resources.getString(R.string.error_login),
                    Toast.LENGTH_SHORT
                ).show()


            }
    }

    private fun startAlertDialog() {
        if (bindingAlertDialog.root.parent != null) (bindingAlertDialog.root.parent as ViewGroup).removeView(
            bindingAlertDialog.root
        )
        val dialog = MaterialAlertDialogBuilder(
            requireContext(),
            R.style.ResetTheme
        )
            .setCancelable(false)
            .setView(bindingAlertDialog.root)
            .show()

        bindingAlertDialog.btnUsePin.setOnClickListener {
            dialog.dismiss()
            navigateFragment(R.id.action_createLoginPasswordFragment_to_createPinFragment)
        }
        bindingAlertDialog.btnUseFingerprint.setOnClickListener {
            dialog.dismiss()
            navigateFragment(R.id.action_createLoginPasswordFragment_to_createFingerprintFragment)
        }
        bindingAlertDialog.dialogAlertCancelButton.setOnClickListener {
            dialog.dismiss()
            navigateFragment(R.id.action_createLoginPasswordFragment_to_profileFragment)
        }
    }

}