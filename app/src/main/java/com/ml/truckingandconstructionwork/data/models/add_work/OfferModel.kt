package com.ml.truckingandconstructionwork.data.models.add_work

import com.google.firebase.firestore.IgnoreExtraProperties
import com.google.firebase.firestore.PropertyName

@IgnoreExtraProperties
data class OfferModel(
    @PropertyName("id")
    val id: String? = null,

    @PropertyName("equipmentType")
    val equipmentType: String? = null,

    @PropertyName("description")
    val description: String? = null
    )
