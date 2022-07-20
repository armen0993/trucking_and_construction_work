package com.ml.truckingandconstructionwork.presentation.ui.settings.edit_profile

import android.os.Build
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.core.widget.doOnTextChanged
import androidx.navigation.fragment.navArgs
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.ml.truckingandconstructionwork.R
import com.ml.truckingandconstructionwork.databinding.AlertDialogSuccessBinding
import com.ml.truckingandconstructionwork.databinding.FragmentEditProfileBinding
import com.ml.truckingandconstructionwork.domain.models.registration.UserDetails
import com.ml.truckingandconstructionwork.presentation.base.BaseFragment
import com.ml.truckingandconstructionwork.presentation.custom_view.EmptyView
import com.ml.truckingandconstructionwork.presentation.utils.MaterialDateDialog
import com.ml.truckingandconstructionwork.presentation.utils.viewBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class EditProfileFragment : BaseFragment<EditProfileViewModel, FragmentEditProfileBinding>() {
    override val viewModel: EditProfileViewModel by viewModel()
    override val binding: FragmentEditProfileBinding by viewBinding()
    private val args: EditProfileFragmentArgs by navArgs()
    private val bindingAlertDialog by lazy { AlertDialogSuccessBinding.inflate(layoutInflater) }
    private lateinit var arrayAdapterCity: ArrayAdapter<String>
    private lateinit var city: String

    override fun onView() {
        binding.toolbar.enableLeftItem(true)
        if (args.userId.isNotEmpty()) {
            viewModel.getUserDetails(args.userId)
        } else {
            binding.emptyView.hide()
        }

        inputFields()
        checkCity()
    }

    override fun onEach() {
        onEach(viewModel.userDetails) {
            getUser(it)
        }
        onEach(viewModel.showProgressBar) {
            showProgress(it)
        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onViewClick() {
        with(binding) {
            btnNext.setOnClickListener {
                if (args.userId.isNotEmpty()) {
                    changeUserDetails()
                } else {
                    Toast.makeText(context, getString(R.string.pleace_sign_in), Toast.LENGTH_SHORT)
                        .show()
                }
            }
            dateOfBirth.setOnClickListener {
                birthOfData()
            }
        }

    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun birthOfData() {
        binding.dateOfBirth.setOnClickListener {
            MaterialDateDialog.showDateValid18Year(
                childFragmentManager, binding.dateOfBirth
            )
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

    private fun changeUserDetails() {
        viewModel.editUserDetails(
            UserDetails(
                id = args.userId,
                name = binding.name.text.toString(),
                surname = binding.surname.text.toString(),
                city = binding.address.text.toString(),
                dataOfBirth = binding.dateOfBirth.text.toString()
            )
        )
        startAlertDialog()
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

        bindingAlertDialog.dialogAlertConfirmButton.setOnClickListener {
            dialog.dismiss()
            popBackStack()
        }
    }

    private fun checkEmptyFields(): Boolean {

        return binding.name.text?.isNotEmpty() == true &&
                binding.surname.text?.isNotEmpty() == true &&
                binding.address.text?.isNotEmpty() == true &&
                binding.dateOfBirth.text?.isNotEmpty() == true

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
            dateOfBirth.doOnTextChanged { _, _, _, _ ->
                btnNext.isEnabled = checkEmptyFields()
            }
        }
    }


    private fun getUser(userDetails: UserDetails) {
        with(binding) {
            name.setText(userDetails.name)
            surname.setText(userDetails.surname)
            dateOfBirth.setText(userDetails.dataOfBirth)
            address.setText(userDetails.city)
        }
    }

    private fun showProgress(show: EmptyView.State) {
        binding.emptyView.visibility = View.VISIBLE
        when (show) {
            EmptyView.State.LOADING -> binding.emptyView.showLoader()
            else -> binding.emptyView.hide()
        }

    }

}