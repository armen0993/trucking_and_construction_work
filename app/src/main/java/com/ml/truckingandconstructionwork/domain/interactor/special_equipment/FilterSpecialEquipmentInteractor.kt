package com.ml.truckingandconstructionwork.domain.interactor.special_equipment

import com.ml.truckingandconstructionwork.core.ActionResult
import com.ml.truckingandconstructionwork.presentation.base.model.BaseModelAdapter

interface FilterSpecialEquipmentInteractor {
suspend operator fun invoke(filter: String): ActionResult<List<BaseModelAdapter<String>>>
}