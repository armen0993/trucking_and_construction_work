package com.ml.truckingandconstructionwork.domain.interactor

import com.ml.truckingandconstructionwork.domain.models.UserDetails

interface SetUserDetailsInteractor {
   suspend operator fun invoke(userDetailsModel: UserDetails)
}