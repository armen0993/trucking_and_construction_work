package com.ml.truckingandconstructionwork.domain.use_case.special_equipment

import com.ml.truckingandconstructionwork.core.ActionResult
import com.ml.truckingandconstructionwork.core.CallException
import com.ml.truckingandconstructionwork.data.repositoryInterface.SpecialEquipmentRepository
import com.ml.truckingandconstructionwork.domain.interactor.special_equipment.GetSpecialEquipmentInteractor
import com.ml.truckingandconstructionwork.domain.models.SearchModel
import com.ml.truckingandconstructionwork.domain.models.special_equipment.SpecialEquipment
import com.ml.truckingandconstructionwork.presentation.base.model.BaseModelAdapter
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class GetSpecialEquipmentUseCase(private val specialEquipmentRepository: SpecialEquipmentRepository) :
    GetSpecialEquipmentInteractor {
    override suspend fun invoke(type: String): ActionResult<List<BaseModelAdapter<String>>> =
        withContext(Dispatchers.IO) {

            when (val apiData = specialEquipmentRepository.getSpecialEquipment()) {
                is ActionResult.Success -> {
                    apiData.data.let {
                        val items = it.map { SpecialEquipment.from(it)}
                        val baseList = mutableListOf<BaseModelAdapter<String>>()
                        baseList.add(SearchModel("19264283723"))
                        val list = mutableListOf<SpecialEquipment>()

                        items.forEach {
                            if (type.lowercase()==it.equipmentType.lowercase()){
                                list.add(it)
                            }else if (type.isEmpty()){
                                list.add(it)
                            }
                        }
                        baseList.addAll(list)
                        ActionResult.Success(baseList)
                    }
                }
                is ActionResult.Error -> {
                    ActionResult.Error(CallException(1001, "error"))
                }
            }
        }
}