package com.ml.truckingandconstructionwork.presentation.ui.main.registration



import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.biometric.BiometricManager
import androidx.biometric.BiometricPrompt
import androidx.core.content.ContextCompat
import androidx.navigation.fragment.findNavController
import com.ml.truckingandconstructionwork.R
import com.ml.truckingandconstructionwork.databinding.FragmentCreateFingerprintBinding
import com.ml.truckingandconstructionwork.presentation.base.BaseFragment
import java.util.concurrent.Executor

class CreateFingerprintFragment :
    BaseFragment<FragmentCreateFingerprintBinding>(FragmentCreateFingerprintBinding::inflate) {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        onClick()
    }

    private fun onClick() {
        with(binding){
            createFingerprint.setOnClickListener {
                alertDialogFingerprint()
            }
            doNotCreate.setOnClickListener {
                navigateFragment(R.id.action_createFingerprintFragment_to_profileFragment)
            }
        }
    }

    private fun alertDialogFingerprint() {
        val executor = ContextCompat.getMainExecutor(requireContext())
        val biometricManager = BiometricManager.from(requireContext())
        fun authUser(executor: Executor) {

            val promptInfo = BiometricPrompt.PromptInfo.Builder()

                .setTitle("Authentication Required!")
                .setSubtitle("Important authentication")
                .setDescription("Please authenticate to be able to view your account information ")
                .setDeviceCredentialAllowed(true)
                .build()

            val biometricPrompt = BiometricPrompt(this, executor,
                object : BiometricPrompt.AuthenticationCallback() {

                    override fun onAuthenticationSucceeded(
                        result: BiometricPrompt.AuthenticationResult
                    ) {

                        navigateFragment(R.id.action_createFingerprintFragment_to_profileFragment)

                    }

                    override fun onAuthenticationError(
                        errorCode: Int, errString: CharSequence
                    ) {
                        super.onAuthenticationError(errorCode, errString)
                        Toast.makeText(
                            context,
                            getString(R.string.fingerprint_not_detected),
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                })
            biometricPrompt.authenticate(promptInfo)
        }

        when (biometricManager.canAuthenticate()) {
            BiometricManager.BIOMETRIC_SUCCESS ->
                authUser(executor)
            BiometricManager.BIOMETRIC_ERROR_NO_HARDWARE ->
                Toast.makeText(
                    requireContext(),
                    "BIOMETRIC_ERROR_NO_HARDWARE",
                    Toast.LENGTH_LONG
                ).show()
            BiometricManager.BIOMETRIC_ERROR_HW_UNAVAILABLE ->
                Toast.makeText(
                    requireContext(),
                    "BIOMETRIC_ERROR_HW_UNAVAILABLE",
                    Toast.LENGTH_LONG
                ).show()
            BiometricManager.BIOMETRIC_ERROR_NONE_ENROLLED ->
                Toast.makeText(
                    requireContext(),
                    "BIOMETRIC_ERROR_NONE_ENROLLED",
                    Toast.LENGTH_LONG
                ).show()
        }
    }
}