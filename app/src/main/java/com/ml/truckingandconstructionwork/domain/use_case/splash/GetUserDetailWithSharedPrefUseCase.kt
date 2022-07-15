package com.ml.truckingandconstructionwork.domain.use_case.splash

import com.ml.truckingandconstructionwork.core.ActionResult
import com.ml.truckingandconstructionwork.core.CallException
import com.ml.truckingandconstructionwork.data.repositoryInterface.UserDetailsRepository
import com.ml.truckingandconstructionwork.domain.interactor.splash.GetUserDetailWithSharedPrefInteractor
import com.ml.truckingandconstructionwork.domain.models.registration.UserDetails
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class GetUserDetailWithSharedPrefUseCase(private val userDetailsRepository: UserDetailsRepository):GetUserDetailWithSharedPrefInteractor {
    override suspend fun invoke(): UserDetails  =
        withContext(Dispatchers.IO){
     return@withContext userDetailsRepository.getUserDetailInSharedPref()

    }
}