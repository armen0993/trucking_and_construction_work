package com.ml.truckingandconstructionwork.presentation.ui.job_offer.offers_list

import androidx.recyclerview.widget.LinearLayoutManager
import com.ml.truckingandconstructionwork.databinding.FragmentOffersListBinding
import com.ml.truckingandconstructionwork.presentation.base.BaseFragment
import com.ml.truckingandconstructionwork.presentation.ui.job_offer.JobOfferFragment
import com.ml.truckingandconstructionwork.presentation.ui.job_offer.offers_list.adapter.OffersListAdapter
import com.ml.truckingandconstructionwork.presentation.utils.viewBinding
import org.koin.androidx.viewmodel.ext.android.viewModel


class OffersListFragment : BaseFragment<OffersListViewModel,FragmentOffersListBinding>() {
    override val viewModel: OffersListViewModel by viewModel()
    override val binding: FragmentOffersListBinding by viewBinding()
    private val offersListAdapter = OffersListAdapter()


    override fun onView() {
        initRecycler()
        binding.toolbar.enableLeftItem(true)
    }

    override fun onViewClick() {
        with(binding){
            toolbar.setOnLeftClickListener {
            }
        }
    }

    private fun initRecycler(){

        with(binding) {
            recyclerListOffers.run {
                context?.let {
                    adapter = offersListAdapter
                    layoutManager =
                        LinearLayoutManager(
                            it,
                            LinearLayoutManager.VERTICAL,
                            false
                        )
                }
            }
        }
    }

}