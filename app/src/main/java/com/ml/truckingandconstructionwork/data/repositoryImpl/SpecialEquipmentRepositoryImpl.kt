package com.ml.truckingandconstructionwork.data.repositoryImpl

import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.ml.truckingandconstructionwork.core.ActionResult
import com.ml.truckingandconstructionwork.core.CallException
import com.ml.truckingandconstructionwork.data.models.special_equipment.SpecialEquipmentModel
import com.ml.truckingandconstructionwork.data.repositoryInterface.SpecialEquipmentRepository
import com.ml.truckingandconstructionwork.data.utils.Constants.SPECIAL_EQUIPMENTS
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.withContext

class SpecialEquipmentRepositoryImpl : SpecialEquipmentRepository {
    private val db = Firebase.firestore


    override suspend fun setSpecialEquipment(specialEquipmentModel: SpecialEquipmentModel): ActionResult<Boolean> {
        return withContext(Dispatchers.IO) {
            db.collection(SPECIAL_EQUIPMENTS)
                .document("${specialEquipmentModel.id}")
                .set(specialEquipmentModel)
            //temp
            ActionResult.Success(true)
        }
    }

    override suspend fun getSpecialEquipment(): ActionResult<List<SpecialEquipmentModel>> {
        val specialEquipmentMap = mutableMapOf<String, SpecialEquipmentModel>()
        val list = mutableListOf<SpecialEquipmentModel>()

        return withContext(Dispatchers.IO) {
            db.collection(SPECIAL_EQUIPMENTS)
                .addSnapshotListener { snapshot, exception ->
                    if (exception != null) {
                        ActionResult.Error(
                            CallException(
                                1001,
                                exception.message ?: "error getData"
                            )
                        )

                    }
                    snapshot?.let {
                        for (change in it.documentChanges) {

                            change.document.toObject(SpecialEquipmentModel::class.java).let { doc ->
                                specialEquipmentMap[doc.id.toString()] = doc
                                list.add(doc)
                            }
                        }
                        ActionResult.Success(list)
                    }
                }


            withContext(Dispatchers.Default) {
                delay(1000)
                ActionResult.Success(list)
            }


        }
    }
}