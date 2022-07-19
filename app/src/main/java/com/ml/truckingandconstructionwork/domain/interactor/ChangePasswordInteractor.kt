package com.ml.truckingandconstructionwork.domain.interactor

import com.ml.truckingandconstructionwork.core.ActionResult
import com.ml.truckingandconstructionwork.domain.models.registration.UserDetails

interface ChangePasswordInteractor {
    suspend operator fun invoke(userDetails: UserDetails,newPassword:String) :ActionResult<Boolean>
}