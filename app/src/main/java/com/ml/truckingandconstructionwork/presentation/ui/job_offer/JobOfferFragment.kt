package com.ml.truckingandconstructionwork.presentation.ui.job_offer

import com.ml.truckingandconstructionwork.databinding.FragmentJobOfferBinding
import com.ml.truckingandconstructionwork.presentation.base.BaseFragment
import com.ml.truckingandconstructionwork.presentation.utils.viewBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class JobOfferFragment : BaseFragment<JobOfferViewModel,FragmentJobOfferBinding>() {

    override val viewModel: JobOfferViewModel by viewModel()
    override val binding: FragmentJobOfferBinding by viewBinding()


    override fun onViewClick() {
        with(binding){
            btnAddOffer.setOnClickListener {
                navigateFragment(JobOfferFragmentDirections.actionJobOfferFragmentToAddJobOfferFragment())
            }
            btnSeeOffer.setOnClickListener {
                navigateFragment(JobOfferFragmentDirections.actionJobOfferFragmentToOffersListFragment())
            }
        }
    }

}