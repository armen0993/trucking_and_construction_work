package com.ml.truckingandconstructionwork.domain.models.add_work

import com.ml.truckingandconstructionwork.data.models.add_work.OfferModel
import com.ml.truckingandconstructionwork.presentation.base.model.DiffUtilModel

data class Offer(
    override val id: String,
    val equipmentType: String,
    val description: String
) : DiffUtilModel<String>() {
    companion object {
        fun from(data:OfferModel)=
            Offer(
                data.id?:"",
                data.equipmentType?:"",
                data.description?:"",
            )
    }
}
