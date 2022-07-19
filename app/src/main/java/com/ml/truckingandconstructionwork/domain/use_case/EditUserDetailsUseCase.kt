package com.ml.truckingandconstructionwork.domain.use_case

import com.ml.truckingandconstructionwork.data.repositoryInterface.UserDetailsRepository
import com.ml.truckingandconstructionwork.domain.interactor.EditUserDetailsInteractor
import com.ml.truckingandconstructionwork.domain.models.registration.UserDetails
import com.ml.truckingandconstructionwork.domain.utils.toRequest
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class EditUserDetailsUseCase(private val userDetailsRepository: UserDetailsRepository):EditUserDetailsInteractor {
    override suspend fun invoke(userDetails: UserDetails) {
        withContext(Dispatchers.IO) {
            userDetailsRepository.editUserDetails(userDetails.toRequest())

        }
    }
}