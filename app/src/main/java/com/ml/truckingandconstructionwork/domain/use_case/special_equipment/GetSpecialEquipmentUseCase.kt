package com.ml.truckingandconstructionwork.domain.use_case.special_equipment

import com.ml.truckingandconstructionwork.core.ActionResult
import com.ml.truckingandconstructionwork.core.CallException
import com.ml.truckingandconstructionwork.data.repositoryInterface.SpecialEquipmentRepository
import com.ml.truckingandconstructionwork.domain.interactor.special_equipment.GetSpecialEquipmentInteractor
import com.ml.truckingandconstructionwork.domain.models.special_equipment.SpecialEquipment
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class GetSpecialEquipmentUseCase(private val specialEquipmentRepository: SpecialEquipmentRepository) :
    GetSpecialEquipmentInteractor {
    override suspend fun invoke(): ActionResult<List<SpecialEquipment>> =
        withContext(Dispatchers.IO) {
            when (val apiData = specialEquipmentRepository.getSpecialEquipment()) {
                is ActionResult.Success -> {
                    apiData.data.let {
                        ActionResult.Success(it.map { SpecialEquipment.from(it) })
                    }
                }
                is ActionResult.Error -> {
                    ActionResult.Error(CallException(1001, "error"))
                }
            }
        }
}