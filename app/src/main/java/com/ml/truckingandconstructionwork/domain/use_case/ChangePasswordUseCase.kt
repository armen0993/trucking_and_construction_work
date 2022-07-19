package com.ml.truckingandconstructionwork.domain.use_case

import com.ml.truckingandconstructionwork.core.ActionResult
import com.ml.truckingandconstructionwork.core.CallException
import com.ml.truckingandconstructionwork.data.repositoryInterface.UserDetailsRepository
import com.ml.truckingandconstructionwork.domain.interactor.ChangePasswordInteractor
import com.ml.truckingandconstructionwork.domain.models.registration.UserDetails
import com.ml.truckingandconstructionwork.domain.utils.toRequest
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class ChangePasswordUseCase(private val userDetailsRepository: UserDetailsRepository) :
    ChangePasswordInteractor {
    override suspend fun invoke(userDetails: UserDetails,newPassword:String): ActionResult<Boolean> =
        withContext(Dispatchers.IO) {
            when (val apiData = userDetailsRepository.getUser(userDetails.id)) {
                is ActionResult.Success -> {
                    apiData.data.let {
                        if (it.password == userDetails.password) {
                            userDetailsRepository.changePassword(userDetails.toRequest(),newPassword)
                            ActionResult.Success(true)
                        } else {
                            ActionResult.Success(false)
                        }
                    }
                }
                is ActionResult.Error -> {
                    ActionResult.Error(CallException(1001, "error"))
                }
            }
        }
}