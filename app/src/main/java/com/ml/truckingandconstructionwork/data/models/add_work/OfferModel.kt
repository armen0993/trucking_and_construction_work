package com.ml.truckingandconstructionwork.data.models.add_work

import com.google.firebase.firestore.IgnoreExtraProperties
import com.google.firebase.firestore.PropertyName

@IgnoreExtraProperties
data class OfferModel(
    @PropertyName("id")
    val id: String? = null,

    @PropertyName("userId")
    val userId:String? = null,

    @PropertyName("equipmentType")
    val equipmentType: String? = null,

    @PropertyName("startAddress")
    val startAddress: String? = null,

    @PropertyName("endAddress")
    val endAddress: String? = null,

    @PropertyName("suggestedPrice")
    val suggestedPrice:String? = null,

    @PropertyName("description")
    val description: String? = null
    )
