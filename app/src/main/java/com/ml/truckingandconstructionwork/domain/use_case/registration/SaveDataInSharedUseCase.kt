package com.ml.truckingandconstructionwork.domain.use_case.registration

import com.ml.truckingandconstructionwork.core.ActionResult
import com.ml.truckingandconstructionwork.data.repositoryInterface.UserDetailsRepository
import com.ml.truckingandconstructionwork.domain.interactor.registration.SaveDataInSharedInteractor
import com.ml.truckingandconstructionwork.domain.models.registration.UserDetails
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class SaveDataInSharedUseCase(private val userDetailsRepository: UserDetailsRepository):SaveDataInSharedInteractor {
    override suspend fun invoke(userId: String) {
        withContext(Dispatchers.IO){
        when(val apiData=userDetailsRepository.getUser(userId)){
            is ActionResult.Success->{
                apiData.data.let {
                    userDetailsRepository.setUserDetailInSharedPref(UserDetails.from(it))
                }
            }
        }
    }}
}