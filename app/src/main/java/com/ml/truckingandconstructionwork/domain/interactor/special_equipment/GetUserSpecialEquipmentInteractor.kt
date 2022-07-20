package com.ml.truckingandconstructionwork.domain.interactor.special_equipment

import com.ml.truckingandconstructionwork.core.ActionResult
import com.ml.truckingandconstructionwork.domain.models.special_equipment.SpecialEquipment

interface GetUserSpecialEquipmentInteractor {
    suspend operator fun invoke(userId:String) :ActionResult<List<SpecialEquipment>>
}
