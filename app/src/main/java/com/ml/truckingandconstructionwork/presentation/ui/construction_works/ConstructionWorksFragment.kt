package com.ml.truckingandconstructionwork.presentation.ui.construction_works

import android.os.Bundle
import android.view.View
import com.ml.truckingandconstructionwork.databinding.FragmentConstructionWorksBinding
import com.ml.truckingandconstructionwork.presentation.base.BaseFragment
import com.ml.truckingandconstructionwork.presentation.base.BaseViewModel
import com.ml.truckingandconstructionwork.presentation.custom_view.EmptyView
import com.ml.truckingandconstructionwork.presentation.utils.viewBinding
import org.koin.androidx.viewmodel.ext.android.viewModel


class ConstructionWorksFragment() : BaseFragment<BaseViewModel,FragmentConstructionWorksBinding>() {
    override val binding: FragmentConstructionWorksBinding by viewBinding()
    override val viewModel: ConstructionWorksViewModel by viewModel()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
       binding.emptyView.showEmpty()

    }





}