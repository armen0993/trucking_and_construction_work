package com.ml.truckingandconstructionwork.domain.use_case.special_equipment

import com.ml.truckingandconstructionwork.core.ActionResult
import com.ml.truckingandconstructionwork.core.CallException
import com.ml.truckingandconstructionwork.data.repositoryInterface.SpecialEquipmentRepository
import com.ml.truckingandconstructionwork.domain.interactor.special_equipment.SetSpecialEquipmentInteractor
import com.ml.truckingandconstructionwork.domain.models.special_equipment.SpecialEquipment
import com.ml.truckingandconstructionwork.domain.utils.toRequest

class SetSpecialEquipmentUseCase(private val specialEquipmentRepository: SpecialEquipmentRepository) :
    SetSpecialEquipmentInteractor {
    override suspend fun invoke(specialEquipment: SpecialEquipment): ActionResult<Boolean> =
        when (val apiData =
            specialEquipmentRepository.setSpecialEquipment(specialEquipment.toRequest())) {
            is ActionResult.Success -> {
                apiData.data.let {
                    ActionResult.Success(it)
                }
            }
            is ActionResult.Error -> {
                ActionResult.Error(CallException(1001, "error use case"))
            }
        }

}