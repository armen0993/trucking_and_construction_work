package com.ml.truckingandconstructionwork.presentation.ui.main.registration

import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.widget.doOnTextChanged
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.navArgs
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.ml.truckingandconstructionwork.R
import com.ml.truckingandconstructionwork.databinding.AlertDialogEnterPinBinding
import com.ml.truckingandconstructionwork.databinding.FragmentPersonalDetailsBinding
import com.ml.truckingandconstructionwork.presentation.Constants.DOC_USER_DETAILS
import com.ml.truckingandconstructionwork.presentation.Constants.USER_DETAILS
import com.ml.truckingandconstructionwork.presentation.base.BaseFragment
import com.ml.truckingandconstructionwork.presentation.models.UserDetails
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch


class PersonalDetailsFragment : BaseFragment<FragmentPersonalDetailsBinding>(
    FragmentPersonalDetailsBinding::inflate
) {

    private val args: PersonalDetailsFragmentArgs by navArgs()
    private val bindingAlertDialog by lazy { AlertDialogEnterPinBinding.inflate(layoutInflater) }
    private val db = Firebase.firestore

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.toolbar.enableLeftItem(true)



//        checkerType(args.userType)

        onClick()
        inputFields()

    }

    private fun onClick() {
        binding.btnNext.setOnClickListener {
            binding
            userDetails()


        }
    }

    private fun userDetails() {
        binding.emptyView.showLoader()
        db.collection(USER_DETAILS).document(DOC_USER_DETAILS)
            .set(
                UserDetails(
                    name = binding.name.text.toString(),
                    surname = binding.surname.text.toString(),
                    city = binding.address.text.toString(),
                    email = binding.email.text.toString(),
                    phoneNumber = binding.phoneNumber.text.toString(),
                    clientType = args.userType
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

    private fun checkEmptyFields(): Boolean {

        return binding.name.text?.isNotEmpty() == true &&
                binding.surname.text?.isNotEmpty() == true &&
                binding.address.text?.isNotEmpty() == true &&
                binding.phoneNumber.text?.isNotEmpty() == true &&
                binding.email.text?.isNotEmpty() == true

    }

    private fun inputFields() {
        with(binding) {
            name.doOnTextChanged { _, _, _, _ ->
                btnNext.isEnabled = checkEmptyFields()
            }
            surname.doOnTextChanged { _, _, _, _ ->
                btnNext.isEnabled = checkEmptyFields()
            }
            address.doOnTextChanged { _, _, _, _ ->
                btnNext.isEnabled = checkEmptyFields()
            }
            phoneNumber.doOnTextChanged { _, _, _, _ ->
                btnNext.isEnabled = checkEmptyFields()
            }
            email.doOnTextChanged { _, _, _, _ ->
                btnNext.isEnabled = checkEmptyFields()
            }
        }
    }

//    private fun checkerType(type:String){
//        when (type) {
//            CLIENT -> {
//                binding.typeUser.text = CLIENT
//            }
//            DRIVER ->
//                binding.typeUser.text = DRIVER
//
//        }
//    }

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

        bindingAlertDialog.dialogAlertConfirmButton.setOnClickListener {
            dialog.dismiss()
            navigateFragment(PersonalDetailsFragmentDirections.actionPersonalDetailsFragmentToCreateLoginPasswordFragment())
        }
        bindingAlertDialog.dialogAlertCancelButton.setOnClickListener {
            dialog.dismiss()
        }
    }

}