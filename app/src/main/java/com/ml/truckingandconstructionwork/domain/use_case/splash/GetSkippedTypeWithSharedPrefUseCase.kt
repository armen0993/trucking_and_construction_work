package com.ml.truckingandconstructionwork.domain.use_case.splash

import com.ml.truckingandconstructionwork.core.ActionResult
import com.ml.truckingandconstructionwork.data.repositoryInterface.UserDetailsRepository
import com.ml.truckingandconstructionwork.domain.interactor.splash.GetSkippedTypeWithSharedPrefInteractor
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class GetSkippedTypeWithSharedPrefUseCase(private val userDetailsRepository: UserDetailsRepository): GetSkippedTypeWithSharedPrefInteractor {
    override suspend fun invoke(): Boolean  =
        withContext(Dispatchers.IO){
            return@withContext userDetailsRepository.getSkippedTypeInSharedPref()

        }
}