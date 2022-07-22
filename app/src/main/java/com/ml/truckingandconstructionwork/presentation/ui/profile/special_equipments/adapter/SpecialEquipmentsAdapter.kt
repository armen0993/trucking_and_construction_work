package com.ml.truckingandconstructionwork.presentation.ui.profile.special_equipments.adapter

import android.content.Context
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View.GONE
import android.view.ViewGroup
import androidx.viewbinding.ViewBinding
import com.ml.truckingandconstructionwork.R
import com.ml.truckingandconstructionwork.databinding.ItemFaqSearchBinding
import com.ml.truckingandconstructionwork.databinding.ItemFaqSearchNoResultBinding
import com.ml.truckingandconstructionwork.databinding.ItemSpecialEquipmentsBinding
import com.ml.truckingandconstructionwork.domain.models.MessageModel
import com.ml.truckingandconstructionwork.domain.models.SearchModel
import com.ml.truckingandconstructionwork.domain.models.special_equipment.SpecialEquipment
import com.ml.truckingandconstructionwork.presentation.base.adapter.BaseAdapter
import com.ml.truckingandconstructionwork.presentation.base.adapter.BaseViewHolder
import com.ml.truckingandconstructionwork.presentation.base.model.BaseModelAdapter
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
    BaseAdapter<ViewBinding, BaseModelAdapter<String>, BaseViewHolder<BaseModelAdapter<String>, ViewBinding>>() {

    var favoriteClick = { _: Boolean -> }
    var seeMoreClick = { _: SpecialEquipment -> }
    var searchTypingFunction = { _: String -> }
    var searchFocusChangeFunction = { _: Boolean -> }
    private var searchText = ""


    companion object {
        private const val LIST_TYPE = 0
        private const val SEARCH = 3
        private const val MESSAGE = 6

    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): BaseViewHolder<BaseModelAdapter<String>, ViewBinding> {
        return when (viewType) {
            LIST_TYPE ->
                SpecialEquipmentsListViewHolder(
                    ItemSpecialEquipmentsBinding.inflate(
                        LayoutInflater.from(parent.context), parent, false
                    )
                ) as BaseViewHolder<BaseModelAdapter<String>, ViewBinding>
            SEARCH ->
                SearchViewHolder(
                    ItemFaqSearchBinding.inflate(
                        LayoutInflater.from(parent.context), parent, false
                    )
                ) as BaseViewHolder<BaseModelAdapter<String>, ViewBinding>
            else ->
                MessageViewHolder(
                    ItemFaqSearchNoResultBinding.inflate(
                        LayoutInflater.from(parent.context), parent, false
                    )
                ) as BaseViewHolder<BaseModelAdapter<String>, ViewBinding>

        }
    }

    override fun getItemViewType(position: Int): Int {
        return when {
            getItem(position) is SpecialEquipment -> {
                LIST_TYPE
            }

            getItem(position) is SearchModel -> {
                SEARCH
            }
            getItem(position) is MessageModel -> {
                MESSAGE
            }
            else -> position
        }
    }

    inner class SpecialEquipmentsListViewHolder(private val binding: ItemSpecialEquipmentsBinding) :
        BaseViewHolder<SpecialEquipment, ViewBinding>(binding) {
        override fun bind(item: SpecialEquipment, context: Context) {
            with(binding) {
                if (item.lengthArrow.isEmpty()) {
                    binding.lengthArrowText.visibility = GONE
                    binding.lengthArrow.visibility = GONE
                } else {
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

    inner class SearchViewHolder(private val binding: ItemFaqSearchBinding) :
        BaseViewHolder<SearchModel, ViewBinding>(binding) {
        override fun bind(item: SearchModel, context: Context) {
            with(binding) {
                faqSearchAnswer.clearSearchFieldFunction = {
                    searchTypingFunction.invoke("")
                    searchText = ""
                }
                faqSearchAnswer.setOnFocusChangeListener { _, hasFocus ->
                    searchFocusChangeFunction.invoke(
                        hasFocus
                    )
                }
                faqSearchAnswer.addTextChangedListener(object : TextWatcher {
                    override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                    }

                    override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                        searchText = p0.toString()
                    }

                    override fun afterTextChanged(p0: Editable?) {
                        searchTypingFunction.invoke(p0.toString())
                    }
                })
            }
        }

    }

    inner class MessageViewHolder(private val binding: ItemFaqSearchNoResultBinding) :
        BaseViewHolder<MessageModel, ViewBinding>(binding) {
        override fun bind(item: MessageModel, context: Context) {
            with(binding) {
                binding.faqMessage.text = item.message
            }
        }
    }


}