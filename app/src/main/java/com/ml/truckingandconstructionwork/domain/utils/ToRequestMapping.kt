package com.ml.truckingandconstructionwork.domain.utils

import com.ml.truckingandconstructionwork.data.models.add_work.OfferModel
import com.ml.truckingandconstructionwork.data.models.registration.UserDetailsModel
import com.ml.truckingandconstructionwork.domain.models.add_work.Offer
import com.ml.truckingandconstructionwork.domain.models.registration.UserDetails

fun UserDetails.toRequest() =
    UserDetailsModel(
        id,
        gender,
        name,
        surname,
        dataOfBirth,
        city,
        email,
        phoneNumber,
        login,
        password,
        clientType
    )

fun Offer.toRequest() =
    OfferModel(
        id,
        equipmentType,
        description
    )