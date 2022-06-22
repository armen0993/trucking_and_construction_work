package com.ml.truckingandconstructionwork.data.data_source_interface

import com.ml.truckingandconstructionwork.core.ActionResult
import com.ml.truckingandconstructionwork.data.models.UserDetailsModel

interface UserDetailsDataSource {
    suspend fun setUserDetails(userDetailsModel: UserDetailsModel)
    suspend fun setUserLoginPassword(userDetailsModel: UserDetailsModel)
    suspend fun getUserDetails(): ActionResult<UserDetailsModel>
}