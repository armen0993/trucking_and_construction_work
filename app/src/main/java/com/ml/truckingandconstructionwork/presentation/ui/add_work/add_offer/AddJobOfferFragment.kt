package com.ml.truckingandconstructionwork.presentation.ui.add_work.add_offer

import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.core.widget.doOnTextChanged
import androidx.navigation.fragment.navArgs
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.ml.truckingandconstructionwork.R
import com.ml.truckingandconstructionwork.databinding.AlertDialogAddJobBinding
import com.ml.truckingandconstructionwork.databinding.FragmentAddJobOfferBinding
import com.ml.truckingandconstructionwork.domain.models.add_work.Offer
import com.ml.truckingandconstructionwork.presentation.base.BaseFragment
import com.ml.truckingandconstructionwork.presentation.custom_view.EmptyView
import com.ml.truckingandconstructionwork.presentation.extensions.GENERATE_ID
import com.ml.truckingandconstructionwork.presentation.utils.viewBinding
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.util.*

class AddJobOfferFragment : BaseFragment<AddJobOfferViewModel, FragmentAddJobOfferBinding>() {
    override val viewModel: AddJobOfferViewModel by viewModel()
    override val binding: FragmentAddJobOfferBinding by viewBinding()
    private lateinit var arrayAdapterSpecialEquipment: ArrayAdapter<String>
    private val bindingAlertDialog by lazy { AlertDialogAddJobBinding.inflate(layoutInflater) }
    private val args:AddJobOfferFragmentArgs by navArgs()


    override fun onView() {
        binding.toolbar.enableLeftItem(true)
        checkSpecialEquipment()
        inputFields()

    }

    override fun onViewClick() {
        with(binding) {
            btnNext.setOnClickListener {
                setDetails()
            }
            imgMapStart.setOnClickListener {
                navigateFragment(AddJobOfferFragmentDirections.actionAddJobOfferFragmentToMapFragment())
            }
            imgMapEnd.setOnClickListener {
                navigateFragment(AddJobOfferFragmentDirections.actionAddJobOfferFragmentToMapFragment())
            }
        }
    }

    override fun onEach() {
        onEach(viewModel.success){
            if (it) {
                startAlertDialog()
            } else {
                Toast.makeText(context, getString(R.string.please_try_again), Toast.LENGTH_SHORT)
                    .show()
            }
        }
        onEach(viewModel.showProgressBar) {
            showProgress(it)
        }
    }

    private fun showProgress(show: EmptyView.State) {
        binding.emptyView.visibility = View.VISIBLE
        when (show) {
            EmptyView.State.LOADING -> binding.emptyView.showLoader()
            else -> binding.emptyView.hide()
        }
    }

    private fun setDetails() {
        viewModel.setOffer(
            Offer(
                id = GENERATE_ID,
                userId = args.userId,
                equipmentType = binding.specialEquipmentType.text.toString(),
                startAddress = binding.startAddress.text.toString(),
                endAddress = binding.endAddress.text.toString(),
                suggestedPrice = binding.price.text.toString(),
                description = binding.jobDescription.text.toString()
            )
        )
    }

    private fun checkEmptyFields(): Boolean {

        return binding.specialEquipmentType.text?.isNotEmpty() == true &&
                binding.startAddress.text?.isNotEmpty() == true &&
                binding.price.text?.isNotEmpty() == true &&
                binding.jobDescription.text?.isNotEmpty() == true

    }

    private fun inputFields() {
        with(binding) {
            specialEquipmentType.doOnTextChanged { p0, _, _, _ ->
                btnNext.isEnabled = checkEmptyFields()
            }
            startAddress.doOnTextChanged { _, _, _, _ ->
                btnNext.isEnabled = checkEmptyFields()
            }
            price.doOnTextChanged { _, _, _, _ ->
                btnNext.isEnabled = checkEmptyFields()
            }
            jobDescription.doOnTextChanged { _, _, _, _ ->
                btnNext.isEnabled = checkEmptyFields()
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

            popBackStack()
        }
    }


}