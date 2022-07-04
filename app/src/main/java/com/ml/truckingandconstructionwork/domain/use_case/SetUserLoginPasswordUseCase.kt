package com.ml.truckingandconstructionwork.domain.use_case

import com.ml.truckingandconstructionwork.data.repositoryInterface.UserDetailsRepository
import com.ml.truckingandconstructionwork.domain.interactor.SetUserLoginPasswordInteractor
import com.ml.truckingandconstructionwork.domain.models.UserDetails
import com.ml.truckingandconstructionwork.domain.utils.toRequest
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class SetUserLoginPasswordUseCase(private val userDetailsRepository: UserDetailsRepository) :
    SetUserLoginPasswordInteractor {

    override suspend fun invoke(userDetails: UserDetails) {
        withContext(Dispatchers.IO) {
            userDetailsRepository.setUserLoginPassword(userDetails.toRequest())
        }
    }
}