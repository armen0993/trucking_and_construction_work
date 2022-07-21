package com.ml.truckingandconstructionwork.domain.models.add_work

import com.ml.truckingandconstructionwork.data.models.add_work.OfferModel
import com.ml.truckingandconstructionwork.presentation.base.model.DiffUtilModel

data class Offer(
    override val id: String,
    val userId:String,
    val equipmentType: String,
    val startAddress: String,
    val endAddress: String,
    val suggestedPrice:String,
    val description: String
) : DiffUtilModel<String>() {
    companion object {
        fun from(data:OfferModel)=
            Offer(
                id = data.id?:"",
                userId = data.userId?:"",
                equipmentType = data.equipmentType?:"",
                startAddress = data.startAddress?:"",
                endAddress = data.endAddress?:"",
                suggestedPrice = data.suggestedPrice?:"",
                description = data.description?:"",
            )
    }
}
