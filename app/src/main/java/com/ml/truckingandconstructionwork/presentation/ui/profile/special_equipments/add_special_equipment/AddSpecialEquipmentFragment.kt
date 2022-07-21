package com.ml.truckingandconstructionwork.presentation.ui.profile.special_equipments.add_special_equipment

import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.core.widget.doOnTextChanged
import androidx.navigation.fragment.navArgs
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.ml.truckingandconstructionwork.R
import com.ml.truckingandconstructionwork.databinding.AlertDialogSuccessBinding
import com.ml.truckingandconstructionwork.databinding.FragmentAddSpecialEquipmentBinding
import com.ml.truckingandconstructionwork.domain.models.special_equipment.SpecialEquipment
import com.ml.truckingandconstructionwork.presentation.base.BaseFragment
import com.ml.truckingandconstructionwork.presentation.custom_view.EmptyView
import com.ml.truckingandconstructionwork.presentation.extensions.GENERATE_ID
import com.ml.truckingandconstructionwork.presentation.utils.viewBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class AddSpecialEquipmentFragment :
    BaseFragment<AddSpecialEquipmentViewModel, FragmentAddSpecialEquipmentBinding>() {
    override val viewModel: AddSpecialEquipmentViewModel by viewModel()
    override val binding: FragmentAddSpecialEquipmentBinding by viewBinding()
    private lateinit var arrayAdapterSpecialEquipment: ArrayAdapter<String>
    private val bindingAlertDialog by lazy { AlertDialogSuccessBinding.inflate(layoutInflater) }
    private val args: AddSpecialEquipmentFragmentArgs by navArgs()

    override fun onView() {
        binding.toolbar.enableLeftItem(true)
        checkSpecialEquipment()
        inputFields()
    }

    override fun onEach() {
        onEach(viewModel.success) {
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


    override fun onViewClick() {
        with(binding) {
            btnNext.setOnClickListener {
                setSpecialEquipment()
            }
        }
    }

    private fun showProgress(show: EmptyView.State) {
        binding.emptyView.visibility = View.VISIBLE
        when (show) {
            EmptyView.State.LOADING -> binding.emptyView.showLoader()
            else -> binding.emptyView.hide()
        }
    }

    private fun setSpecialEquipment() {
        viewModel.setSpecialEquipment(
            SpecialEquipment(
                GENERATE_ID,
                args.userId,
                binding.specialEquipmentType.text.toString(),
                binding.brand.text.toString(),
                binding.model.text.toString(),
                binding.weight.text.toString(),
                binding.capacity.text.toString(),
                binding.lengthArrow.text.toString(),
                binding.productionYear.text.toString(),
            )
        )
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

    private fun checkEmptyFields(): Boolean {

        return binding.specialEquipmentType.text?.isNotEmpty() == true &&
                binding.model.text?.isNotEmpty() == true &&
                binding.weight.text?.isNotEmpty() == true &&
                binding.productionYear.text?.isNotEmpty() == true

    }

    private fun inputFields() {
        with(binding) {
            specialEquipmentType.doOnTextChanged { p0, _, _, _ ->
                btnNext.isEnabled = checkEmptyFields()
            }
            model.doOnTextChanged { _, _, _, _ ->
                btnNext.isEnabled = checkEmptyFields()
            }
            weight.doOnTextChanged { _, _, _, _ ->
                btnNext.isEnabled = checkEmptyFields()
            }
            productionYear.doOnTextChanged { _, _, _, _ ->
                btnNext.isEnabled = checkEmptyFields()
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