package com.ml.truckingandconstructionwork.presentation.ui.registration.personal_details

import android.app.Activity
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
import com.ml.truckingandconstructionwork.presentation.base.BaseFragment
import com.ml.truckingandconstructionwork.domain.models.registration.UserDetails
import com.ml.truckingandconstructionwork.presentation.extensions.mask
import com.ml.truckingandconstructionwork.presentation.utils.Constants
import com.ml.truckingandconstructionwork.presentation.utils.CustomDateDialogTools
import com.ml.truckingandconstructionwork.presentation.utils.viewBinding
import com.ml.truckingandconstructionwork.databinding.AlertDialogEnterPinBinding
import com.ml.truckingandconstructionwork.databinding.FragmentPersonalDetailsBinding
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.util.concurrent.ThreadLocalRandom
import java.util.concurrent.TimeUnit

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

        binding.phoneNumber.mask("(__) __ __ __",binding.phoneNumber)


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
                // Этот обратный вызов будет вызываться в двух случаях:
                // 1 - Мгновенная проверка. В некоторых случаях номер телефона может быть мгновенно
                // проверено без отправки или ввода кода подтверждения.
                // 2 - Автопоиск. На некоторых устройствах сервисы Google Play могут автоматически
                // обнаружим входящее СМС с подтверждением и выполним проверку без
                // действие пользователя.

                Log.d(TAG, "onVerificationCompleted:$credential")
                signInWithPhoneAuthCredential(credential)
            }

            override fun onVerificationFailed(e: FirebaseException) {
                // Этот обратный вызов вызывается при недопустимом запросе на проверку,
                // например, если формат номера телефона недействителен.
                Log.w(TAG, "onVerificationFailed", e)

                if (e is FirebaseAuthInvalidCredentialsException) {
                    // Неверный запрос
                } else if (e is FirebaseTooManyRequestsException) {
                    // Превышена квота SMS для проекта
                }

                // Показать сообщение и обновить интерфейс
            }

            override fun onCodeSent(
                verificationId: String,
                token: PhoneAuthProvider.ForceResendingToken
            ) {
                // SMS-код подтверждения отправлен на указанный номер телефона, мы
                // теперь нужно попросить пользователя ввести код, а затем создать учетные данные
                // комбинируя код с идентификатором проверки.
                Log.d(TAG, "onCodeSent:$verificationId")

                // Сохраняем идентификатор подтверждения и токен повторной отправки, чтобы мы могли использовать их позже
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
                        // Вход выполнен успешно, обновите пользовательский интерфейс с информацией о вошедшем в систему пользователе
                        Log.d(TAG, "signInWithCredential:success")

                        val user = task.result?.user
                    } else {
                        // Не удалось войти, отобразить сообщение и обновить пользовательский интерфейс
                        Log.w(TAG, "signInWithCredential:failure", task.exception)
                        if (task.exception is FirebaseAuthInvalidCredentialsException) {
                            // Введенный код подтверждения недействителен
                        }
                        // Update UI
                    }
                }
        }
    }


    private fun userDetails() {
      //  userId = ThreadLocalRandom.current().nextInt(1000, 9999);
        viewModel.setUserDetails(
            UserDetails(
                //id = id,
                name = binding.name.text.toString(),
                surname = binding.surname.text.toString(),
                city = binding.address.text.toString(),
                email = binding.email.text.toString(),
                phoneNumber = "+374${binding.phoneNumber.text?.replace("[^0-9]".toRegex(),"")}",
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
                binding.dateOfBirth.text?.isNotEmpty() == true &&
                binding.address.text?.isNotEmpty() == true &&
                binding.phoneNumber.text?.isNotEmpty() == true &&
                binding.checkBox.isChecked &&
                binding.email.text?.isNotEmpty() == true

    }

    private fun inputFields() {
        with(binding) {
            name.doOnTextChanged { p0, _, _, _ ->
                btnNext.isEnabled = checkEmptyFields()
            }
            surname.doOnTextChanged { _, _, _, _ ->
                btnNext.isEnabled = checkEmptyFields()
            }
            dateOfBirth.doOnTextChanged { _, _, _, _ ->
                btnNext.isEnabled = checkEmptyFields()
            }
            address.doOnTextChanged { _, _, _, _ ->
                btnNext.isEnabled = checkEmptyFields()
            }
            phoneNumber.doOnTextChanged { _, _, _, _ ->




                if (binding.phoneNumber.isFocused){
                    binding.phoneNumber.hint = "(98) 00 00 00"
                }

                btnNext.isEnabled = checkEmptyFields()
            }
            email.doOnTextChanged { _, _, _, _ ->
                btnNext.isEnabled = checkEmptyFields()
            }
        }
    }

    private fun startAlertDialog() {
         checkerPhoneNumber(binding.phoneNumber.text.toString())
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
            if (storedVerificationId== bindingAlertDialog.dialogAlertMessage.toString()){
                navigateFragment(
                    PersonalDetailsFragmentDirections.actionPersonalDetailsFragmentToProfileFragment(

                    )
                )
            }

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