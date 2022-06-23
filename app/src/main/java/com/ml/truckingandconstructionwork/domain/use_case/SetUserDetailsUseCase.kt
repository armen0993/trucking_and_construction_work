package com.ml.truckingandconstructionwork.domain.use_case

import com.ml.truckingandconstructionwork.core.ActionResult
import com.ml.truckingandconstructionwork.core.CallException
import com.ml.truckingandconstructionwork.domain.interactor.SetUserDetailsInteractor
import com.ml.truckingandconstructionwork.data.models.UserDetailsModel
import com.ml.truckingandconstructionwork.data.repositoryInterface.UserDetailsRepository
import com.ml.truckingandconstructionwork.domain.models.UserDetails
import com.ml.truckingandconstructionwork.domain.utils.toRequest
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class SetUserDetailsUseCase(private val userDetailsRepository: UserDetailsRepository) :
    SetUserDetailsInteractor {

    override suspend fun invoke(userDetails: UserDetails) {
        withContext(Dispatchers.IO) {

            userDetailsRepository.setUserDetails(userDetails.toRequest())

        }
    }
}