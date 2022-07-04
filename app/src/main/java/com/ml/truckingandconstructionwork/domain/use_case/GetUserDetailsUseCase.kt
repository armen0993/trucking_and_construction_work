package com.ml.truckingandconstructionwork.domain.use_case

import com.ml.truckingandconstructionwork.core.ActionResult
import com.ml.truckingandconstructionwork.core.CallException
import com.ml.truckingandconstructionwork.data.repositoryInterface.UserDetailsRepository
import com.ml.truckingandconstructionwork.domain.interactor.GetUserDetailsInteractor
import com.ml.truckingandconstructionwork.domain.models.UserDetails
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class GetUserDetailsUseCase(private val userDetailsRepository: UserDetailsRepository) :
    GetUserDetailsInteractor {

    override suspend fun invoke(): ActionResult<UserDetails> =
        withContext(Dispatchers.IO) {
            when (val apiData = userDetailsRepository.getUserDetails()) {
                is ActionResult.Success -> {
                    apiData.data.let {
                        ActionResult.Success(UserDetails.from(it))
                    }
                }
                is ActionResult.Error->{
                    ActionResult.Error(CallException(1001,"error"))
                }

            }
        }


}