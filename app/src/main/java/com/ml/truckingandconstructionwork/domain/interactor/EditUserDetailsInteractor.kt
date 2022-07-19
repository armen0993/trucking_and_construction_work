package com.ml.truckingandconstructionwork.domain.interactor

import com.ml.truckingandconstructionwork.domain.models.registration.UserDetails

interface EditUserDetailsInteractor {
    suspend operator fun invoke(userDetails: UserDetails)
}