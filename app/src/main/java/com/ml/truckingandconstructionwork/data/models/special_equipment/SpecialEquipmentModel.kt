package com.ml.truckingandconstructionwork.data.models.special_equipment

import com.google.firebase.firestore.PropertyName

data class SpecialEquipmentModel(
    @PropertyName("id")
   val id: String? = null,
    @PropertyName("userId")
   val userId: String? = null,
    @PropertyName("equipmentType")
   val equipmentType: String? = null,
    @PropertyName("carBrand")
    val carBrand:String?=null,
    @PropertyName("model")
    val model:String? = null,
    @PropertyName("weight")
    val weight:String? = null,
    @PropertyName("capacity")
    val capacity:String? = null,
    @PropertyName("lengthArrow")
    val lengthArrow:String? = null,
    @PropertyName("productionYear")
    val productionYear:String?=null
)
