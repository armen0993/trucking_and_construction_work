package com.ml.truckingandconstructionwork.presentation.ui.special_equipment

import android.os.Bundle
import android.view.View
import com.ml.truckingandconstructionwork.databinding.FragmentSpecialEquipmentBinding
import com.ml.truckingandconstructionwork.presentation.base.BaseFragment
import com.ml.truckingandconstructionwork.presentation.base.BaseViewModel
import com.ml.truckingandconstructionwork.presentation.utils.viewBinding
import org.koin.androidx.viewmodel.ext.android.viewModel


class SpecialEquipmentFragment() : BaseFragment<BaseViewModel,FragmentSpecialEquipmentBinding>() {
    override val binding: FragmentSpecialEquipmentBinding by viewBinding()
    override val viewModel: SpecialEquipmentViewModel by viewModel()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
       binding.emptyView.showEmpty()

    }





}