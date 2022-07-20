package com.ml.truckingandconstructionwork.presentation.ui.profile.special_equipments.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.viewbinding.ViewBinding
import com.ml.truckingandconstructionwork.databinding.ItemSpecialEquipmentsBinding
import com.ml.truckingandconstructionwork.domain.models.special_equipment.SpecialEquipment
import com.ml.truckingandconstructionwork.presentation.base.adapter.BaseAdapter
import com.ml.truckingandconstructionwork.presentation.base.adapter.BaseViewHolder

class SpecialEquipmentsAdapter :
    BaseAdapter<ViewBinding, SpecialEquipment, BaseViewHolder<SpecialEquipment, ViewBinding>>() {
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
            with(binding){
                equipmentType.text = item.equipmentType
                description.text = item.model
            }

        }
    }
}