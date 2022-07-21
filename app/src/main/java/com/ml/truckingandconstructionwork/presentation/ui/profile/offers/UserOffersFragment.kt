package com.ml.truckingandconstructionwork.presentation.ui.profile.offers

import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.ml.truckingandconstructionwork.databinding.FragmentUserOffersBinding
import com.ml.truckingandconstructionwork.domain.models.add_work.Offer
import com.ml.truckingandconstructionwork.domain.models.special_equipment.SpecialEquipment
import com.ml.truckingandconstructionwork.presentation.base.BaseFragment
import com.ml.truckingandconstructionwork.presentation.custom_view.EmptyView
import com.ml.truckingandconstructionwork.presentation.ui.offers.adapter.OffersListAdapter
import com.ml.truckingandconstructionwork.presentation.utils.viewBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class UserOffersFragment : BaseFragment<UserOffersViewModel, FragmentUserOffersBinding>() {
    override val viewModel: UserOffersViewModel by viewModel()
    override val binding: FragmentUserOffersBinding by viewBinding()
    private val offersListAdapter = OffersListAdapter()
    private val args:UserOffersFragmentArgs by navArgs()

    override fun onView() {
        binding.toolbar.enableLeftItem(true)
        viewModel.getOffersList(args.userId)
        initRecycler()
    }

    override fun onEach() {
        onEach(viewModel.showProgressBar) {
            showProgress(it)
        }

        onEach(viewModel.listOffer) {
            setListInAdapter(it)
        }
    }

    private fun setListInAdapter(listOffers: List<Offer>) {
        offersListAdapter.submitList(listOffers)

    }

    private fun showProgress(show: EmptyView.State) {
        when (show) {
            EmptyView.State.LOADING -> binding.emptyView.showLoader()
            EmptyView.State.EMPTY -> binding.emptyView.showEmpty()
            else -> binding.emptyView.hide()
        }
    }

    private fun initRecycler() {
        with(binding) {
            recyclerMyOffers.run {
                adapter = offersListAdapter
                layoutManager =
                    LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
            }
        }
    }
}