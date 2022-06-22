package com.ml.truckingandconstructionwork.presentation.ui.trucks

import android.os.Bundle
import android.view.View
import com.ml.truckingandconstructionwork.databinding.FragmentForgotPasswordBinding
import com.ml.truckingandconstructionwork.databinding.FragmentTruksBinding

import com.ml.truckingandconstructionwork.presentation.base.BaseFragment
import com.ml.truckingandconstructionwork.presentation.base.BaseViewModel
import com.ml.truckingandconstructionwork.presentation.custom_view.EmptyView
import com.ml.truckingandconstructionwork.presentation.utils.viewBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class TrucksFragment() : BaseFragment<BaseViewModel,FragmentTruksBinding>() {

    override val binding: FragmentTruksBinding by viewBinding()
    override val viewModel: TrucksViewModel by viewModel()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

       binding.emptyView.showLoader()
    }





}