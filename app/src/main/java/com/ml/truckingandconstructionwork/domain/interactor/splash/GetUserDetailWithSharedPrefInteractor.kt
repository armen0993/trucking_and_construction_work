package com.ml.truckingandconstructionwork.domain.interactor.splash

import com.ml.truckingandconstructionwork.core.ActionResult
import com.ml.truckingandconstructionwork.domain.models.registration.UserDetails

interface GetUserDetailWithSharedPrefInteractor {
    suspend operator fun invoke(): UserDetails
}