package com.ml.truckingandconstructionwork.domain.utils

import com.ml.truckingandconstructionwork.data.models.add_work.OfferModel
import com.ml.truckingandconstructionwork.data.models.registration.UserDetailsModel
import com.ml.truckingandconstructionwork.data.models.special_equipment.SpecialEquipmentModel
import com.ml.truckingandconstructionwork.domain.models.add_work.Offer
import com.ml.truckingandconstructionwork.domain.models.registration.UserDetails
import com.ml.truckingandconstructionwork.domain.models.special_equipment.SpecialEquipment

fun UserDetails.toRequest() =
    UserDetailsModel(
        id,
        gender,
        name,
        surname,
        dataOfBirth,
        city,
        phoneNumber,
        email,
        password,
        clientType
    )

fun Offer.toRequest() =
    OfferModel(
        id,
        equipmentType,
        description
    )

fun SpecialEquipment.toRequest()=
    SpecialEquipmentModel(
        id, userId, model, weight, capacity, lengthArrow, productionYear
    )