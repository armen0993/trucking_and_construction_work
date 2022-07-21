package com.ml.truckingandconstructionwork.domain.models.special_equipment

import com.ml.truckingandconstructionwork.data.models.special_equipment.SpecialEquipmentModel
import com.ml.truckingandconstructionwork.presentation.base.model.DiffUtilModel

data class SpecialEquipment(
    override val id: String,
    val userId:String,
    val equipmentType:String,
    val carBrand:String,
    val model: String,
    val weight: String,
    val capacity: String,
    val lengthArrow: String,
    val productionYear:String
) : DiffUtilModel<String>() {
    companion object {
        fun from(data: SpecialEquipmentModel) =
            SpecialEquipment(
                data.id ?: "",
                data.userId?:"",
                data.equipmentType?:"",
                data.carBrand?:"",
                data.model ?: "",
                data.weight ?: "",
                data.capacity ?: "",
                data.lengthArrow ?: "",
                data.productionYear ?: ""
            )
    }
}
