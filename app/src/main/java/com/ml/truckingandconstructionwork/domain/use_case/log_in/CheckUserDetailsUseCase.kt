package com.ml.truckingandconstructionwork.domain.use_case.log_in

import com.ml.truckingandconstructionwork.core.ActionResult
import com.ml.truckingandconstructionwork.core.CallException
import com.ml.truckingandconstructionwork.data.repositoryInterface.UserDetailsRepository
import com.ml.truckingandconstructionwork.domain.interactor.log_in.CheckUserDetailsInteractor
import com.ml.truckingandconstructionwork.domain.models.registration.UserDetails
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class CheckUserDetailsUseCase(private val userDetailsRepository: UserDetailsRepository) :
    CheckUserDetailsInteractor {
    override suspend fun invoke(userDetails: UserDetails): ActionResult<String?> =
        withContext(Dispatchers.IO) {
            when (val apiData = userDetailsRepository.getUserDetails()) {
                is ActionResult.Success -> {
                    apiData.data.let {
                        var userId=""
                        it.forEach {
                            if (it.email == userDetails.email && it.password == userDetails.password) {
                                userId= it.id.toString()

                            }
                        }
                        ActionResult.Success(userId)
                    }
                }
                is ActionResult.Error -> {
                    ActionResult.Error(CallException(1001, "null"))
                }
            }
        }
}