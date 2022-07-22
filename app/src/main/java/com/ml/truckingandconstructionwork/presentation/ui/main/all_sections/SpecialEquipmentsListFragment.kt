package com.ml.truckingandconstructionwork.presentation.ui.main.all_sections

import android.view.View.GONE
import android.view.View.VISIBLE
import android.view.ViewGroup
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.ml.truckingandconstructionwork.R
import com.ml.truckingandconstructionwork.databinding.AlertDialogSeeMoreBinding
import com.ml.truckingandconstructionwork.databinding.FragmentSpecialEquipmentsListBinding
import com.ml.truckingandconstructionwork.domain.models.MessageModel
import com.ml.truckingandconstructionwork.domain.models.SearchModel
import com.ml.truckingandconstructionwork.domain.models.registration.UserDetails
import com.ml.truckingandconstructionwork.domain.models.special_equipment.SpecialEquipment
import com.ml.truckingandconstructionwork.presentation.base.BaseFragment
import com.ml.truckingandconstructionwork.presentation.base.model.BaseModelAdapter
import com.ml.truckingandconstructionwork.presentation.custom_view.EmptyView
import com.ml.truckingandconstructionwork.presentation.ui.main.filter.BottomSheetFilterSpecialEquipmentFragment
import com.ml.truckingandconstructionwork.presentation.ui.profile.special_equipments.adapter.SpecialEquipmentsAdapter
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
    private var userDetails = UserDetails()
    private var searchString = ""
    private var needToShow = false
    private lateinit var listSpecialEquipments: List<BaseModelAdapter<String>>
    private val bottomFilter = BottomSheetFilterSpecialEquipmentFragment()

    override fun onView() {
        with(binding.toolbar) {
            enableLeftItem(true)
            enableRightItem(true)
        }
        initRecycler()
        if (args.equipmentType.isNotEmpty()) {
            viewModel.getSpecialEquipmentList(args.equipmentType)
            binding.toolbar.setTitleText(args.equipmentType)
        } else {
            viewModel.getSpecialEquipmentList(equipmentType)
            binding.toolbar.setTitleText(resources.getString(R.string.special_equipments))
        }

        searchFocusChangeFunction()
        searchTypingFunction()




    }

    override fun onEach() {
        onEach(viewModel.showProgressBar) {
            showProgress(it)
        }

        onEach(viewModel.showProgressBarAlert) {
            showProgressAlert(it)
        }

        onEach(viewModel.listSpecialEquipment) {
            showList(it)
        }
        onEach(viewModel.userDetails) {
            userDetails = it
        }

        onEach(viewModel.listFilterSpecialEquipment) {
           showSearchResult(it)
        }
    }

    override fun onViewClick() {
        specialEquipmentsAdapter.seeMoreClick = {
            viewModel.getUserDetails(it.userId)
            setData(it, userDetails)
            startAlertDialogSeeMore()
        }
        with(binding){
            toolbar.setOnRightClickListener {
                bottomFilter.show(parentFragmentManager,"")
            }
        }

    }

    private fun showSearchResult(specialEquipment: List<BaseModelAdapter<String>>) {
        specialEquipmentsAdapter.submitList(specialEquipment)
    }

    private fun searchFocusChangeFunction(){
        specialEquipmentsAdapter.searchFocusChangeFunction = {
            needToShow = it
            if (it && searchString.isEmpty()) startWritingSearch()
            if (!it && searchString.isEmpty()) setListInAdapter(listSpecialEquipments)
        }
    }

    private fun searchTypingFunction(){
        specialEquipmentsAdapter.searchTypingFunction = {
            searchString = it
            if (it.isNotEmpty()) viewModel.filterEquipmentType(it)
            if (it.isEmpty())startWritingSearch()
            if (it.isEmpty() && !needToShow) setListInAdapter(listSpecialEquipments)
        }
    }

    private fun startWritingSearch() {
        if (!needToShow) return
        needToShow = false
        val messageList = mutableListOf<BaseModelAdapter<String>>()
        messageList.add(SearchModel("19264283723"))
        val message = getString(R.string.faq_start_search)
        messageList.add(MessageModel(message,"0"))
        specialEquipmentsAdapter.submitList(messageList)
    }

    private fun setData(specialEquipment: SpecialEquipment, userDetails: UserDetails) {
        with(bindingAlertSeeMore) {

            brandText.text = specialEquipment.carBrand
            modelText.text = specialEquipment.model
            weightText.text = specialEquipment.weight
            capacityText.text = specialEquipment.capacity
            if (specialEquipment.lengthArrow.isNotEmpty()) {
                lengthArrowText.text = specialEquipment.lengthArrow
            } else {
                lengthArrow.visibility = GONE
                lengthArrowText.visibility = GONE
            }
            productionYearText.text = specialEquipment.productionYear
            userNameText.text = userDetails.name
            userSurnameText.text = userDetails.surname
            phoneNumberText.text = userDetails.phoneNumber
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
            else -> bindingAlertSeeMore.iconEquipment.setImageResource(R.drawable.ic_empty)
        }

    }

    private fun showList(listSpecialEquipment:  List<BaseModelAdapter<String>>) {
        this.listSpecialEquipments = listSpecialEquipment
        setListInAdapter(listSpecialEquipment)
    }
    private fun setListInAdapter(listSpecialEquipment:  List<BaseModelAdapter<String>>) {

        specialEquipmentsAdapter.submitList(listSpecialEquipment)

    }

    private fun showProgress(show: EmptyView.State) {
        when (show) {
            EmptyView.State.LOADING -> binding.emptyView.showLoader()
            EmptyView.State.EMPTY -> binding.emptyView.showEmpty()
            else -> binding.emptyView.hide()
        }
    }

    private fun showProgressAlert(show: EmptyView.State) {
        when (show) {
            EmptyView.State.LOADING -> bindingAlertSeeMore.emptyView.showLoader()
            EmptyView.State.EMPTY -> bindingAlertSeeMore.emptyView.showEmpty()
            else -> bindingAlertSeeMore.emptyView.hide()
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