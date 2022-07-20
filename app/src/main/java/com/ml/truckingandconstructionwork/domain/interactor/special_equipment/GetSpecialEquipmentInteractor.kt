package com.ml.truckingandconstructionwork.domain.interactor.special_equipment

import com.ml.truckingandconstructionwork.core.ActionResult
import com.ml.truckingandconstructionwork.domain.models.special_equipment.SpecialEquipment

interface GetSpecialEquipmentInteractor {
    suspend operator fun invoke():ActionResult<List<SpecialEquipment>>
}