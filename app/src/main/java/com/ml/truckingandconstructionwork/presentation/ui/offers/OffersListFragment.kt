package com.ml.truckingandconstructionwork.presentation.ui.offers

import android.view.View.VISIBLE
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.ml.truckingandconstructionwork.databinding.FragmentOffersListBinding
import com.ml.truckingandconstructionwork.domain.models.add_work.Offer
import com.ml.truckingandconstructionwork.presentation.base.BaseFragment
import com.ml.truckingandconstructionwork.presentation.custom_view.EmptyView
import com.ml.truckingandconstructionwork.presentation.ui.offers.adapter.OffersListAdapter
import com.ml.truckingandconstructionwork.presentation.utils.viewBinding
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class OffersListFragment : BaseFragment<OffersListViewModel, FragmentOffersListBinding>() {
    override val viewModel: OffersListViewModel by viewModel()
    override val binding: FragmentOffersListBinding by viewBinding()
    private val offersListAdapter = OffersListAdapter()


    override fun onView() {
        initRecycler()
        viewModel.getOffersList()

        lifecycleScope.launch {
            viewModel.listOffers.collect {
                setListInAdapter(it)
            }
        }

        lifecycleScope.launch {
            viewModel.showProgressBar.collect {
                binding.emptyView.visibility = VISIBLE
                binding.emptyView.setState(it)
            }
        }

    }

    fun scrollToTop() {
        binding.recyclerListOffers.scrollToPosition(0)
    }

    override fun onViewClick() {

    }

//    override fun onEach() {
//        onEach(viewModel.showProgressBar) {
//            showProgress(it)
//        }
//
//        onEach(viewModel.listOffers) {
//            setListInAdapter(it)
//        }
//    }

    private fun showProgress(show: EmptyView.State) {


//             EmptyView.State.LOADING-> binding.emptyView.showLoader()
//             EmptyView.State.EMPTY-> binding.emptyView.showEmpty()
//            else -> binding.emptyView.hide()

}

private fun setListInAdapter(listOffers: List<Offer>) {
    offersListAdapter.submitList(listOffers)

}

private fun initRecycler() {
    with(binding) {
        recyclerListOffers.run {
            adapter = offersListAdapter
            recyclerListOffers.layoutManager =
                LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        }
    }
}

}