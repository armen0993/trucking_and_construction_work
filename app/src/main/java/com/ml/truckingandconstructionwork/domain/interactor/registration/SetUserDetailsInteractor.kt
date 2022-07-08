package com.ml.truckingandconstructionwork.domain.interactor.registration

import com.ml.truckingandconstructionwork.domain.models.registration.UserDetails

interface SetUserDetailsInteractor {
   suspend operator fun invoke(userDetailsModel: UserDetails)
}