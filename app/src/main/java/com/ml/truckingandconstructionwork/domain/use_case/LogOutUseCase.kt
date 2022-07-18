package com.ml.truckingandconstructionwork.domain.use_case

import com.ml.truckingandconstructionwork.data.repositoryInterface.UserDetailsRepository
import com.ml.truckingandconstructionwork.domain.interactor.LogOutInteractor

class LogOutUseCase(private val userDetailsRepository: UserDetailsRepository):LogOutInteractor {
    override suspend fun invoke() {
       userDetailsRepository.logOut()
    }
}