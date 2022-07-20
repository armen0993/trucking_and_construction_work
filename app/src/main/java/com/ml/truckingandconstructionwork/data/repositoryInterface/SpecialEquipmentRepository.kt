package com.ml.truckingandconstructionwork.data.repositoryInterface

import com.ml.truckingandconstructionwork.core.ActionResult
import com.ml.truckingandconstructionwork.data.models.special_equipment.SpecialEquipmentModel

interface SpecialEquipmentRepository {
    suspend fun setSpecialEquipment(specialEquipmentModel: SpecialEquipmentModel): ActionResult<Boolean>
    suspend fun getSpecialEquipment(): ActionResult<List<SpecialEquipmentModel>>
}