package com.ml.truckingandconstructionwork.domain.interactor

import com.ml.truckingandconstructionwork.domain.models.UserDetails

interface SetUserLoginPasswordInteractor {
   suspend operator fun invoke(userDetailsModel: UserDetails)
}