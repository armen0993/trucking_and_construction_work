package com.ml.truckingandconstructionwork.presentation.ui.registration.personal_details

import android.app.Activity
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.core.widget.doOnTextChanged
import androidx.navigation.fragment.navArgs
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.firebase.FirebaseException
import com.google.firebase.FirebaseTooManyRequestsException
import com.google.firebase.auth.*
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.ml.truckingandconstructionwork.R
import com.ml.truckingandconstructionwork.databinding.AlertDialogEnterPinBinding
import com.ml.truckingandconstructionwork.databinding.FragmentPersonalDetailsBinding
import com.ml.truckingandconstructionwork.presentation.base.BaseFragment
import com.ml.truckingandconstructionwork.data.models.UserDetailsModel
import com.ml.truckingandconstructionwork.domain.models.UserDetails
import com.ml.truckingandconstructionwork.presentation.utils.Constants
import com.ml.truckingandconstructionwork.presentation.utils.CustomDateDialogTools
import com.ml.truckingandconstructionwork.presentation.utils.viewBinding
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.util.concurrent.ThreadLocalRandom
import java.util.concurrent.TimeUnit
import kotlin.random.Random

class PersonalDetailsFragment() :
    BaseFragment<PersonalDetailsViewModel, FragmentPersonalDetailsBinding>(

    ) {
    private lateinit var auth: FirebaseAuth
    private lateinit var callbacks: PhoneAuthProvider.OnVerificationStateChangedCallbacks
    override val binding: FragmentPersonalDetailsBinding by viewBinding()
    override val viewModel: PersonalDetailsViewModel by viewModel()
    private var storedVerificationId: String? = ""
    private lateinit var resendToken: PhoneAuthProvider.ForceResendingToken
    private val args: PersonalDetailsFragmentArgs by navArgs()
    private val bindingAlertDialog by lazy { AlertDialogEnterPinBinding.inflate(layoutInflater) }
    private lateinit var arrayAdapterCity: ArrayAdapter<String>
    private lateinit var city: String
    private lateinit var gender: String
    private var userId = -1

    override fun onView() {
        callBackAuth()
        binding.toolbar.enableLeftItem(true)
        inputFields()
        checkCity()
        getGender()

    }

    override fun onEach() {
        onEach(viewModel.showProgressBar) {
            showProgress(it)
        }
        onEach(viewModel.startAlertConfirmPhoneNumber) {
            if (it) {
                startAlertDialog()
            } else {
                Toast.makeText(context, "Please check your data", Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun onViewClick() {
        with(binding) {
            btnNext.setOnClickListener {
                userDetails()
            }
            dateOfBirth.setOnClickListener {
                birthOfData()
            }
            btnMale.setOnClickListener {
                getGender()
            }
            btnFemale.setOnClickListener {
                getGender()
            }
        }
    }

    private fun checkCity() {
        val getCity = resources.getStringArray(R.array.city)
        arrayAdapterCity =
            context?.let { ArrayAdapter(it, R.layout.item_city, getCity) }!!
        binding.address.setAdapter(arrayAdapterCity)

        binding.address.setOnClickListener {
            binding.address.showDropDown()
        }

        binding.address.doOnTextChanged { text, start, before, count ->
            city = text.toString()
            if (!binding.address.text.isNullOrEmpty()) {
                binding.addressContainer.isErrorEnabled = false
                binding.addressContainer.error = null
            }
        }
    }

    private fun callBackAuth() {
        auth = Firebase.auth
        callbacks = object : PhoneAuthProvider.OnVerificationStateChangedCallbacks() {

            override fun onVerificationCompleted(credential: PhoneAuthCredential) {
                // This callback will be invoked in two situations:
                // 1 - Instant verification. In some cases the phone number can be instantly
                //     verified without needing to send or enter a verification code.
                // 2 - Auto-retrieval. On some devices Google Play services can automatically
                //     detect the incoming verification SMS and perform verification without
                //     user action.

                Log.d(TAG, "onVerificationCompleted:$credential")
                signInWithPhoneAuthCredential(credential)
            }

            override fun onVerificationFailed(e: FirebaseException) {
                // This callback is invoked in an invalid request for verification is made,
                // for instance if the the phone number format is not valid.
                Log.w(TAG, "onVerificationFailed", e)

                if (e is FirebaseAuthInvalidCredentialsException) {
                    // Invalid request
                } else if (e is FirebaseTooManyRequestsException) {
                    // The SMS quota for the project has been exceeded
                }

                // Show a message and update the UI
            }

            override fun onCodeSent(
                verificationId: String,
                token: PhoneAuthProvider.ForceResendingToken
            ) {
                // The SMS verification code has been sent to the provided phone number, we
                // now need to ask the user to enter the code and then construct a credential
                // by combining the code with a verification ID.
                Log.d(TAG, "onCodeSent:$verificationId")

                // Save verification ID and resending token so we can use them later
                storedVerificationId = verificationId
                resendToken = token
            }
        }
    }

    private fun signInWithPhoneAuthCredential(credential: PhoneAuthCredential) {
        activity?.let {
            auth.signInWithCredential(credential)
                .addOnCompleteListener(it) { task ->
                    if (task.isSuccessful) {
                        // Sign in success, update UI with the signed-in user's information
                        Log.d(TAG, "signInWithCredential:success")

                        val user = task.result?.user
                    } else {
                        // Sign in failed, display a message and update the UI
                        Log.w(TAG, "signInWithCredential:failure", task.exception)
                        if (task.exception is FirebaseAuthInvalidCredentialsException) {
                            // The verification code entered was invalid
                        }
                        // Update UI
                    }
                }
        }
    }


    private fun userDetails() {
        userId = ThreadLocalRandom.current().nextInt(1000, 9999);
        viewModel.setUserDetails(
            UserDetails(
                userid = userId,
                name = binding.name.text.toString(),
                surname = binding.surname.text.toString(),
                city = binding.address.text.toString(),
                email = binding.email.text.toString(),
                phoneNumber = binding.phoneNumber.text.toString(),
                clientType = args.userType,
                dataOfBirth = binding.dateOfBirth.text.toString(),
                gender = gender,
                login = "",
                password = ""
            )
        )


    }

    private fun getGender() {
        if (binding.btnMale.isChecked) {
            gender = binding.btnMale.text.toString()
        } else if (binding.btnFemale.isChecked) {
            gender = binding.btnFemale.text.toString()
        }

    }

    private fun checkerPhoneNumber(phoneNumber: String) {
        val options = PhoneAuthOptions.newBuilder(auth)
            .setPhoneNumber(phoneNumber)       // Phone number to verify
            .setTimeout(60L, TimeUnit.SECONDS) // Timeout and unit
            .setActivity(context as Activity)                 // Activity (for callback binding)
            .setCallbacks(callbacks)          // OnVerificationStateChangedCallbacks
            .build()
        PhoneAuthProvider.verifyPhoneNumber(options)
    }

    private fun showProgress(show: Boolean) {
        if (show) binding.emptyView.showLoader() else binding.emptyView.hide()
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


    private fun startAlertDialog() {
        // checkerPhoneNumber(binding.phoneNumber.text.toString())
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
            navigateFragment(
                PersonalDetailsFragmentDirections.actionPersonalDetailsFragmentToCreateLoginPasswordFragment(
                    userId
                )
            )
        }
        bindingAlertDialog.dialogAlertCancelButton.setOnClickListener {
            dialog.dismiss()
        }
    }

    private fun birthOfData() {
        binding.dateOfBirth.setOnClickListener {
            binding.dateOfBirth.also {
                context?.let { it1 ->
                    CustomDateDialogTools.createDateDialog(
                        it1,
                        Constants.FORMAT_DATE,
                        it
                    )
                }
            }
        }
    }

    companion object {
        private const val TAG = "PersonalDetailFragment"
    }

}