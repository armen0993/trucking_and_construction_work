package com.ml.truckingandconstructionwork.presentation.ui.add_work

import com.ml.truckingandconstructionwork.databinding.FragmentAddWorkBinding
import com.ml.truckingandconstructionwork.presentation.base.BaseFragment
import com.ml.truckingandconstructionwork.presentation.base.BaseViewModel
import com.ml.truckingandconstructionwork.presentation.utils.Constants.USER_ID
import com.ml.truckingandconstructionwork.presentation.utils.viewBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class AddWorkFragment : BaseFragment<BaseViewModel,FragmentAddWorkBinding>() {
    override val binding: FragmentAddWorkBinding by viewBinding()
    override val viewModel: AddWorkViewModel by viewModel()
    var userId = ""

    override fun onView() {
      userId = arguments?.getString(USER_ID).toString()
    }

    override fun onViewClick() {
        binding.btnAddOffer.setOnClickListener {
            navigateFragment(AddWorkFragmentDirections.actionAddWorkFragmentToAddJobOfferFragment().setUserId(userId))
        }
    }





}