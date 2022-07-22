package com.ml.truckingandconstructionwork.domain.interactor.special_equipment

import com.ml.truckingandconstructionwork.core.ActionResult
import com.ml.truckingandconstructionwork.domain.models.special_equipment.SpecialEquipment
import com.ml.truckingandconstructionwork.presentation.base.model.BaseModelAdapter

interface GetSpecialEquipmentInteractor {
    suspend operator fun invoke(type: String):ActionResult<List<BaseModelAdapter<String>>>
}