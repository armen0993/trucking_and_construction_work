package com.ml.truckingandconstructionwork.presentation.ui.main.all_sections

import android.view.ViewGroup
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.ml.truckingandconstructionwork.R
import com.ml.truckingandconstructionwork.databinding.AlertDialogSavePasswordBinding
import com.ml.truckingandconstructionwork.databinding.AlertDialogSeeMoreBinding
import com.ml.truckingandconstructionwork.databinding.FragmentSpecialEquipmentsListBinding
import com.ml.truckingandconstructionwork.domain.models.special_equipment.SpecialEquipment
import com.ml.truckingandconstructionwork.presentation.base.BaseFragment
import com.ml.truckingandconstructionwork.presentation.custom_view.EmptyView
import com.ml.truckingandconstructionwork.presentation.ui.profile.special_equipments.adapter.SpecialEquipmentsAdapter
import com.ml.truckingandconstructionwork.presentation.ui.sign_in.SignInFragmentDirections
import com.ml.truckingandconstructionwork.presentation.utils.Constants.CONCRETE_MIXER
import com.ml.truckingandconstructionwork.presentation.utils.Constants.CONCRETE_PUMP
import com.ml.truckingandconstructionwork.presentation.utils.Constants.CRAWLER_EXCAVATOR
import com.ml.truckingandconstructionwork.presentation.utils.Constants.DUMP_TRUCK
import com.ml.truckingandconstructionwork.presentation.utils.Constants.EXCAVATOR_LOADER
import com.ml.truckingandconstructionwork.presentation.utils.Constants.LOADER
import com.ml.truckingandconstructionwork.presentation.utils.Constants.MANIPULATOR
import com.ml.truckingandconstructionwork.presentation.utils.Constants.ROAD_ROLLER
import com.ml.truckingandconstructionwork.presentation.utils.Constants.TOW_TRUCK
import com.ml.truckingandconstructionwork.presentation.utils.Constants.TRUCKS
import com.ml.truckingandconstructionwork.presentation.utils.Constants.TRUCK_CRANE
import com.ml.truckingandconstructionwork.presentation.utils.Constants.WHEELED_EXCAVATOR
import com.ml.truckingandconstructionwork.presentation.utils.viewBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class SpecialEquipmentsListFragment :
    BaseFragment<SpecialEquipmentsListViewModel, FragmentSpecialEquipmentsListBinding>() {
    override val viewModel: SpecialEquipmentsListViewModel by viewModel()
    override val binding: FragmentSpecialEquipmentsListBinding by viewBinding()
    private val specialEquipmentsAdapter = SpecialEquipmentsAdapter()
    private val equipmentType = ""
    private val args: SpecialEquipmentsListFragmentArgs by navArgs()
    private val bindingAlertSeeMore by lazy {
        AlertDialogSeeMoreBinding.inflate(
            layoutInflater
        )
    }

    override fun onView() {
        binding.toolbar.enableLeftItem(true)
        initRecycler()
        if (args.equipmentType.isNotEmpty()) {
            viewModel.getSpecialEquipmentList(args.equipmentType)
        } else {
            viewModel.getSpecialEquipmentList(equipmentType)
        }

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
        specialEquipmentsAdapter.seeMoreClick = {
            setData(it)
            startAlertDialogSeeMore()

        }
        specialEquipmentsAdapter.favoriteClick={
            if (it){

            }
        }
    }

    private fun setData(specialEquipment: SpecialEquipment) {
        with(bindingAlertSeeMore) {
            brandText.text = specialEquipment.carBrand
            modelText.text = specialEquipment.model
            weightText.text = specialEquipment.weight
            capacityText.text = specialEquipment.capacity
            lengthArrowText.text = specialEquipment.lengthArrow
            productionYearText.text = specialEquipment.productionYear
        }

        when (specialEquipment.equipmentType.lowercase().replace("\\s".toRegex(), "")) {
            TRUCKS -> bindingAlertSeeMore.iconEquipment.setImageResource(R.drawable.ic_truck)
            DUMP_TRUCK -> bindingAlertSeeMore.iconEquipment.setImageResource(R.drawable.ic_dump_truck)
            TRUCK_CRANE -> bindingAlertSeeMore.iconEquipment.setImageResource(R.drawable.ic_crane)
            MANIPULATOR -> bindingAlertSeeMore.iconEquipment.setImageResource(R.drawable.ic_manipulator)
            CRAWLER_EXCAVATOR -> bindingAlertSeeMore.iconEquipment.setImageResource(R.drawable.ic_excavator)
            WHEELED_EXCAVATOR -> bindingAlertSeeMore.iconEquipment.setImageResource(R.drawable.ic_welled_excavator)
            EXCAVATOR_LOADER -> bindingAlertSeeMore.iconEquipment.setImageResource(R.drawable.ic_excavator_loader)
            CONCRETE_MIXER -> bindingAlertSeeMore.iconEquipment.setImageResource(R.drawable.ic_concrete_mixer)
            CONCRETE_PUMP -> bindingAlertSeeMore.iconEquipment.setImageResource(R.drawable.ic_concrete_pump)
            TOW_TRUCK -> bindingAlertSeeMore.iconEquipment.setImageResource(R.drawable.ic_tow_truck)
            LOADER -> bindingAlertSeeMore.iconEquipment.setImageResource(R.drawable.ic_loader)
            ROAD_ROLLER -> bindingAlertSeeMore.iconEquipment.setImageResource(R.drawable.ic_road_roller)
            else->bindingAlertSeeMore.iconEquipment.setImageResource(R.drawable.ic_empty)
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

    private fun startAlertDialogSeeMore() {
        if (bindingAlertSeeMore.root.parent != null) (bindingAlertSeeMore.root.parent as ViewGroup).removeView(
            bindingAlertSeeMore.root
        )
        val dialog = MaterialAlertDialogBuilder(
            requireContext(),
            R.style.ResetTheme
        )
            .setCancelable(false)
            .setView(bindingAlertSeeMore.root)
            .show()

        bindingAlertSeeMore.btnNo.setOnClickListener {
            dialog.dismiss()
        }
    }

}