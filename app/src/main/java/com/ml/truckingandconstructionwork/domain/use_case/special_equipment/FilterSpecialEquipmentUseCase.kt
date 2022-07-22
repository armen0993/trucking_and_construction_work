package com.ml.truckingandconstructionwork.domain.use_case.special_equipment

import com.ml.truckingandconstructionwork.core.ActionResult
import com.ml.truckingandconstructionwork.core.CallException
import com.ml.truckingandconstructionwork.data.repositoryInterface.SpecialEquipmentRepository
import com.ml.truckingandconstructionwork.domain.interactor.special_equipment.FilterSpecialEquipmentInteractor
import com.ml.truckingandconstructionwork.domain.models.MessageModel
import com.ml.truckingandconstructionwork.domain.models.SearchModel
import com.ml.truckingandconstructionwork.domain.models.special_equipment.SpecialEquipment
import com.ml.truckingandconstructionwork.presentation.base.model.BaseModelAdapter
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class FilterSpecialEquipmentUseCase(private val specialEquipmentRepository: SpecialEquipmentRepository) :
    FilterSpecialEquipmentInteractor {
    override suspend fun invoke(filter: String): ActionResult<List<BaseModelAdapter<String>>> =
        withContext(Dispatchers.IO) {
            when (val apiData = specialEquipmentRepository.getSpecialEquipment()) {
                is ActionResult.Success -> {
                    apiData.data.let {
                        val items = it.map { SpecialEquipment.from(it) }
                        val baseList = mutableListOf<BaseModelAdapter<String>>()
                        baseList.add(SearchModel("19264283723"))
                        items.forEach {
                            if (it.equipmentType.lowercase().contains(filter.lowercase()) ||
                                it.carBrand.lowercase().contains(filter.lowercase()) ||
                                it.model.lowercase().contains(filter.lowercase())
                            ) baseList.add(it)
                        }

                        if (baseList.size == 1) {
                            val message = "По запросу «$filter» ничего не найдено"
                            baseList.add(MessageModel(message, "0"))
                        }
                        ActionResult.Success(baseList)
                    }
                }
                is ActionResult.Error -> {
                    ActionResult.Error(
                        CallException(
                            apiData.errors.errorCode,
                            apiData.errors.errorMessage
                        )
                    )
                }
            }
        }
}