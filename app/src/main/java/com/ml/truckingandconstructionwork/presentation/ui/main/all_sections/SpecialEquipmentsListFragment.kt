package com.ml.truckingandconstructionwork.presentation.ui.main.all_sections

import androidx.recyclerview.widget.LinearLayoutManager
import com.ml.truckingandconstructionwork.databinding.FragmentSpecialEquipmentsListBinding
import com.ml.truckingandconstructionwork.domain.models.special_equipment.SpecialEquipment
import com.ml.truckingandconstructionwork.presentation.base.BaseFragment
import com.ml.truckingandconstructionwork.presentation.custom_view.EmptyView
import com.ml.truckingandconstructionwork.presentation.ui.profile.special_equipments.adapter.SpecialEquipmentsAdapter
import com.ml.truckingandconstructionwork.presentation.utils.viewBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class SpecialEquipmentsListFragment : BaseFragment<SpecialEquipmentsListViewModel,FragmentSpecialEquipmentsListBinding>() {
    override val viewModel: SpecialEquipmentsListViewModel by viewModel()
    override val binding: FragmentSpecialEquipmentsListBinding by viewBinding()
    private val specialEquipmentsAdapter = SpecialEquipmentsAdapter()

    override fun onView() {
        binding.toolbar.enableLeftItem(true)
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
            recyclerSpecialEquipmentsList.run {
                adapter = specialEquipmentsAdapter
                layoutManager =
                    LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
            }
        }
    }

}