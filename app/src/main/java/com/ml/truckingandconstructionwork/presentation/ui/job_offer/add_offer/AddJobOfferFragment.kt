package com.ml.truckingandconstructionwork.presentation.ui.job_offer.add_offer

import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.core.widget.doOnTextChanged
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.ml.truckingandconstructionwork.R
import com.ml.truckingandconstructionwork.databinding.AlertDialogAddJobBinding
import com.ml.truckingandconstructionwork.databinding.AlertDialogEnterPinBinding
import com.ml.truckingandconstructionwork.databinding.FragmentAddJobOfferBinding
import com.ml.truckingandconstructionwork.presentation.base.BaseFragment
import com.ml.truckingandconstructionwork.presentation.ui.sign_in.registration.personal_details.PersonalDetailsFragmentDirections
import com.ml.truckingandconstructionwork.presentation.utils.viewBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class AddJobOfferFragment : BaseFragment<AddJobOfferViewModel, FragmentAddJobOfferBinding>() {
    override val viewModel: AddJobOfferViewModel by viewModel()
    override val binding: FragmentAddJobOfferBinding by viewBinding()
    private lateinit var arrayAdapterSpecialEquipment: ArrayAdapter<String>
    private val bindingAlertDialog by lazy { AlertDialogAddJobBinding.inflate(layoutInflater) }


    override fun onView() {
        binding.toolbar.enableLeftItem(true)
        checkSpecialEquipment()

        //temp
        binding.btnNext.isEnabled = true
    }

    override fun onViewClick() {
        with(binding){
            btnNext.setOnClickListener {
                startAlertDialog()
            }
        }
    }



    private fun checkSpecialEquipment() {
        val getSpecialEquipment = resources.getStringArray(R.array.special_equipment)
        arrayAdapterSpecialEquipment =
            context?.let { ArrayAdapter(it, R.layout.item_city, getSpecialEquipment) }!!
        binding.specialEquipmentType.setAdapter(arrayAdapterSpecialEquipment)

        binding.specialEquipmentType.setOnClickListener {
            binding.specialEquipmentType.showDropDown()
        }

        binding.specialEquipmentType.doOnTextChanged { text, start, before, count ->

            if (!binding.specialEquipmentType.text.isNullOrEmpty()) {
                binding.specialEquipmentTypeContainer.isErrorEnabled = false
                binding.specialEquipmentTypeContainer.error = null
            }
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

        bindingAlertDialog.dialogAlertConfirmButton.setOnClickListener {
            dialog.dismiss()
                navigateFragment(
                   AddJobOfferFragmentDirections.actionAddJobOfferFragmentToOffersListFragment()
                )
        }
        bindingAlertDialog.dialogAlertCancelButton.setOnClickListener {
            dialog.dismiss()
            popBackStack()
        }
    }


}