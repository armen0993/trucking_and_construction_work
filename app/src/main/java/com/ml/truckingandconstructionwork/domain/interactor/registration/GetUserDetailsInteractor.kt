package com.ml.truckingandconstructionwork.domain.interactor.registration

import com.ml.truckingandconstructionwork.core.ActionResult
import com.ml.truckingandconstructionwork.domain.models.registration.UserDetails

interface GetUserDetailsInteractor {
   suspend operator fun invoke():ActionResult<List<UserDetails>>
}