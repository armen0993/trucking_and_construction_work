package com.ml.truckingandconstructionwork.presentation.ui.offers.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Button
import androidx.viewbinding.ViewBinding
import com.ml.truckingandconstructionwork.databinding.ItemListOffersBinding
import com.ml.truckingandconstructionwork.domain.models.add_work.Offer
import com.ml.truckingandconstructionwork.presentation.base.adapter.BaseAdapter
import com.ml.truckingandconstructionwork.presentation.base.adapter.BaseViewHolder

class OffersListAdapter :
    BaseAdapter<ViewBinding, Offer, BaseViewHolder<Offer, ViewBinding>>() {
   var orderClick = {_:Button->}

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): BaseViewHolder<Offer, ViewBinding> {
       return OffersListViewHolder(
            ItemListOffersBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        ) as BaseViewHolder<Offer, ViewBinding>
    }

    inner class OffersListViewHolder(private val binding: ItemListOffersBinding) :
        BaseViewHolder<Offer, ViewBinding>(binding) {
        override fun bind(item: Offer, context: Context) {
            with(binding){
                equipmentType.text = item.equipmentType
                description.text = item.description
            }
        }

        override fun onItemClick(item: Offer) {
            orderClick.invoke(binding.orderButton)
        }
    }
}