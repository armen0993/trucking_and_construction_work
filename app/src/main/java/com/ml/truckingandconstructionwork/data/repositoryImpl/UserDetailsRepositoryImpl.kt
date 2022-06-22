package com.ml.truckingandconstructionwork.data.repositoryImpl

import com.ml.truckingandconstructionwork.core.ActionResult
import com.ml.truckingandconstructionwork.core.CallException
import com.ml.truckingandconstructionwork.data.data_source_interface.UserDetailsDataSource
import com.ml.truckingandconstructionwork.data.models.UserDetailsModel
import com.ml.truckingandconstructionwork.data.repositoryInterface.UserDetailsRepository

class UserDetailsRepositoryImpl(
    private val userDetailsDataSource: UserDetailsDataSource
) : UserDetailsRepository {
    override suspend fun setUserDetails(userDetailsModel: UserDetailsModel) {
        userDetailsDataSource.setUserDetails(userDetailsModel)
        }

    override suspend fun setUserLoginPassword(userDetailsModel: UserDetailsModel) {
        userDetailsDataSource.setUserLoginPassword(userDetailsModel)
    }

    override suspend fun getUserDetails(): ActionResult<UserDetailsModel> =
        when (val apiData = userDetailsDataSource.getUserDetails()) {
            is ActionResult.Success -> {
                ActionResult.Success(apiData.data)
            }
            is ActionResult.Error -> {
                ActionResult.Error(
                    CallException(
                        1001,
                        "error set UserDetailsModel"
                    )
                )
            }
        }



}