package com.ml.truckingandconstructionwork.domain.use_case

import com.ml.truckingandconstructionwork.core.ActionResult
import com.ml.truckingandconstructionwork.core.CallException
import com.ml.truckingandconstructionwork.data.repositoryInterface.UserDetailsRepository
import com.ml.truckingandconstructionwork.domain.interactor.GetUserInteractor
import com.ml.truckingandconstructionwork.domain.models.registration.UserDetails
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class GetUserUseCase(private val userDetailsRepository: UserDetailsRepository) : GetUserInteractor {
    override suspend fun invoke(userId: String): ActionResult<UserDetails> =
        withContext(Dispatchers.IO) {
            when (val apiData = userDetailsRepository.getUser(userId)) {
                is ActionResult.Success -> {
                    apiData.data.let {
                        ActionResult.Success(UserDetails.from(it))
                    }
                }
                is ActionResult.Error -> {
                    ActionResult.Error(CallException(1001, "error use case"))
                }
            }
        }

}