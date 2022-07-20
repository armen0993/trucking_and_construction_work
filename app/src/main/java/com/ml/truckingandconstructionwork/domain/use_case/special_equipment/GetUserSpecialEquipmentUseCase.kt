package com.ml.truckingandconstructionwork.domain.use_case.special_equipment

import com.ml.truckingandconstructionwork.core.ActionResult
import com.ml.truckingandconstructionwork.core.CallException
import com.ml.truckingandconstructionwork.data.repositoryInterface.SpecialEquipmentRepository
import com.ml.truckingandconstructionwork.domain.interactor.special_equipment.GetUserSpecialEquipmentInteractor
import com.ml.truckingandconstructionwork.domain.models.special_equipment.SpecialEquipment
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class GetUserSpecialEquipmentUseCase (
    private val specialEquipmentRepository: SpecialEquipmentRepository
        ): GetUserSpecialEquipmentInteractor {
    override suspend fun invoke(userId: String): ActionResult<List<SpecialEquipment>> =
        withContext(Dispatchers.IO){
            val list = mutableListOf<SpecialEquipment>()
            when(val apiData = specialEquipmentRepository.getSpecialEquipment()){
                is ActionResult.Success->{
                    apiData.data.let { listData->
                        listData.forEach {
                            if (it.userId == userId){
                                list.add( SpecialEquipment.from(it) )
                            }
                        }
                        ActionResult.Success(list)
                    }
                }
                is ActionResult.Error->{
                    ActionResult.Error(CallException(1001,"error"))
                }
            }
        }
}