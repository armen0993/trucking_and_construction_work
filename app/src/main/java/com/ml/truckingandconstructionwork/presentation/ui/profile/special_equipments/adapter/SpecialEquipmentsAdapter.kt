package com.ml.truckingandconstructionwork.presentation.ui.profile.special_equipments.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View.GONE
import android.view.ViewGroup
import androidx.viewbinding.ViewBinding
import com.ml.truckingandconstructionwork.R
import com.ml.truckingandconstructionwork.databinding.ItemSpecialEquipmentsBinding
import com.ml.truckingandconstructionwork.domain.models.special_equipment.SpecialEquipment
import com.ml.truckingandconstructionwork.presentation.base.adapter.BaseAdapter
import com.ml.truckingandconstructionwork.presentation.base.adapter.BaseViewHolder
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

class SpecialEquipmentsAdapter :
    BaseAdapter<ViewBinding, SpecialEquipment, BaseViewHolder<SpecialEquipment, ViewBinding>>() {

    var favoriteClick = { _: Boolean -> }
    var seeMoreClick = { _: SpecialEquipment -> }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): BaseViewHolder<SpecialEquipment, ViewBinding> {
        return SpecialEquipmentsListViewHolder(
            ItemSpecialEquipmentsBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        ) as BaseViewHolder<SpecialEquipment, ViewBinding>
    }

    inner class SpecialEquipmentsListViewHolder(private val binding: ItemSpecialEquipmentsBinding) :
        BaseViewHolder<SpecialEquipment, ViewBinding>(binding) {
        override fun bind(item: SpecialEquipment, context: Context) {
            with(binding) {
                if (item.lengthArrow.isEmpty()){
                    binding.lengthArrowText.visibility = GONE
                    binding.lengthArrow.visibility = GONE
                }else{
                    lengthArrowText.text = item.lengthArrow
                }
                equipmentType.text = item.equipmentType
                brand.text = item.carBrand
                model.text = item.model
                weightText.text = item.weight
                capacity.text = item.capacity

                productionYearText.text = item.productionYear
            }
            when (item.equipmentType.lowercase().replace("\\s".toRegex(), "")) {
                TRUCKS -> binding.iconEquipment.setImageResource(R.drawable.ic_truck)
                DUMP_TRUCK -> binding.iconEquipment.setImageResource(R.drawable.ic_dump_truck)
                TRUCK_CRANE -> binding.iconEquipment.setImageResource(R.drawable.ic_crane)
                MANIPULATOR -> binding.iconEquipment.setImageResource(R.drawable.ic_manipulator)
                CRAWLER_EXCAVATOR -> binding.iconEquipment.setImageResource(R.drawable.ic_excavator)
                WHEELED_EXCAVATOR -> binding.iconEquipment.setImageResource(R.drawable.ic_welled_excavator)
                EXCAVATOR_LOADER -> binding.iconEquipment.setImageResource(R.drawable.ic_excavator_loader)
                CONCRETE_MIXER -> binding.iconEquipment.setImageResource(R.drawable.ic_concrete_mixer)
                CONCRETE_PUMP -> binding.iconEquipment.setImageResource(R.drawable.ic_concrete_pump)
                TOW_TRUCK -> binding.iconEquipment.setImageResource(R.drawable.ic_tow_truck)
                LOADER -> binding.iconEquipment.setImageResource(R.drawable.ic_loader)
                ROAD_ROLLER -> binding.iconEquipment.setImageResource(R.drawable.ic_road_roller)
                else -> binding.iconEquipment.setImageResource(R.drawable.ic_empty)
            }
        }

        override fun onItemClick(item: SpecialEquipment) {
            favoriteClick.invoke(true)
            seeMoreClick.invoke(item)
        }
    }


}