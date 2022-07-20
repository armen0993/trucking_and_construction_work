package com.ml.truckingandconstructionwork.domain.interactor.special_equipment

import com.ml.truckingandconstructionwork.core.ActionResult
import com.ml.truckingandconstructionwork.domain.models.special_equipment.SpecialEquipment

interface SetSpecialEquipmentInteractor {
    suspend operator fun invoke(specialEquipment: SpecialEquipment):ActionResult<Boolean>
}