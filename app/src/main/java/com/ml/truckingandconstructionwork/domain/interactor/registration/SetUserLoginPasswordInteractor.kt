package com.ml.truckingandconstructionwork.domain.interactor.registration

import com.ml.truckingandconstructionwork.domain.models.registration.UserDetails

interface SetUserLoginPasswordInteractor {
   suspend operator fun invoke(userDetailsModel: UserDetails)
}