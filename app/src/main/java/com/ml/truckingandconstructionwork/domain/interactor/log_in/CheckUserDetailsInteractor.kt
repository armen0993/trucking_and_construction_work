package com.ml.truckingandconstructionwork.domain.interactor.log_in

import com.ml.truckingandconstructionwork.core.ActionResult
import com.ml.truckingandconstructionwork.domain.models.registration.UserDetails

interface CheckUserDetailsInteractor {
    suspend operator fun invoke(userDetails: UserDetails) : ActionResult<String?>
}