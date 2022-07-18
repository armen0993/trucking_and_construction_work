package com.ml.truckingandconstructionwork.domain.interactor

import com.ml.truckingandconstructionwork.core.ActionResult
import com.ml.truckingandconstructionwork.domain.models.registration.UserDetails

interface GetUserInteractor {
    suspend operator fun invoke(userId:String): ActionResult<UserDetails>
}