package com.ml.truckingandconstructionwork.domain.interactor

import com.ml.truckingandconstructionwork.core.ActionResult
import com.ml.truckingandconstructionwork.domain.models.UserDetails

interface GetUserDetailsInteractor {
   suspend operator fun invoke():ActionResult<UserDetails>
}