package com.ml.truckingandconstructionwork.presentation.ui.profile.special_equipments

import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.ml.truckingandconstructionwork.databinding.FragmentSpecialEquipmentsBinding
import com.ml.truckingandconstructionwork.domain.models.add_work.Offer
import com.ml.truckingandconstructionwork.domain.models.special_equipment.SpecialEquipment
import com.ml.truckingandconstructionwork.presentation.base.BaseFragment
import com.ml.truckingandconstructionwork.presentation.custom_view.EmptyView
import com.ml.truckingandconstructionwork.presentation.ui.profile.special_equipments.adapter.SpecialEquipmentsAdapter
import com.ml.truckingandconstructionwork.presentation.utils.viewBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class SpecialEquipmentsFragment : BaseFragment<SpecialEquipmentsViewModel, FragmentSpecialEquipmentsBinding>() {
    override val viewModel: SpecialEquipmentsViewModel by viewModel()
    override val binding: FragmentSpecialEquipmentsBinding by viewBinding()
    private val specialEquipmentsAdapter = SpecialEquipmentsAdapter()
    private val args:SpecialEquipmentsFragmentArgs by navArgs()

    override fun onView() {
       with(binding.toolbar){
           enableLeftItem(true)
           enableRightItem(true)
       }
        viewModel.getSpecialEquipmentList(args.userId)
        initRecycler()
    }

    override fun onEach() {
        onEach(viewModel.showProgressBar) {
            showProgress(it)
        }

        onEach(viewModel.listSpecialEquipment) {
            setListInAdapter(it)
        }
    }



    override fun onViewClick() {
        with(binding){
            toolbar.setOnRightClickListener {
                navigateFragment(SpecialEquipmentsFragmentDirections.actionSpecialEquipmentsFragmentToAddSpecialEquipmentFragment().setUserId(args.userId))
            }
        }
    }

    private fun setListInAdapter(listSpecialEquipment: List<SpecialEquipment>) {
        specialEquipmentsAdapter.submitList(listSpecialEquipment)

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
            recyclerSpecialEquipments.run {
                adapter = specialEquipmentsAdapter
                layoutManager =
                    LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
            }
        }
    }

}