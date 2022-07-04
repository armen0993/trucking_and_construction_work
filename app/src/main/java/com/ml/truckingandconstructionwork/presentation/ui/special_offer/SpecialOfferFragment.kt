package com.ml.truckingandconstructionwork.presentation.ui.special_offer

import com.ml.truckingandconstructionwork.databinding.FragmentSpecialOfferBinding
import com.ml.truckingandconstructionwork.presentation.base.BaseFragment
import com.ml.truckingandconstructionwork.presentation.utils.viewBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class SpecialOfferFragment : BaseFragment<SpecialOfferViewModel,FragmentSpecialOfferBinding>() {

    override val viewModel: SpecialOfferViewModel by viewModel()
    override val binding: FragmentSpecialOfferBinding by viewBinding()

}