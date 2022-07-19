package com.ml.truckingandconstructionwork.domain.use_case.log_in

import com.ml.truckingandconstructionwork.data.repositoryInterface.UserDetailsRepository
import com.ml.truckingandconstructionwork.domain.interactor.log_in.SetSkippedTypeInSharedInteractor

class SetSkippedTypeInSharedUseCase(private val userDetailsRepository: UserDetailsRepository): SetSkippedTypeInSharedInteractor {
    override suspend fun invoke(boolean: Boolean) {
        userDetailsRepository.setSkippedTypeInSharedPref(boolean)
    }
}