package com.ml.truckingandconstructionwork.domain.utils

import com.ml.truckingandconstructionwork.data.models.UserDetailsModel
import com.ml.truckingandconstructionwork.domain.models.UserDetails

fun UserDetails.toRequest():UserDetailsModel =
    UserDetailsModel(
        userid,
        gender,
        name,
        surname,
        dataOfBirth,
        city,
        email,
        phoneNumber,
        login,
        password,
        clientType)