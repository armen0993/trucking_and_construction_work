package com.ml.truckingandconstructionwork.data.repositoryInterface

import com.ml.truckingandconstructionwork.core.ActionResult
import com.ml.truckingandconstructionwork.data.models.UserDetailsModel


interface UserDetailsRepository {
    suspend fun setUserDetails(userDetailsModel: UserDetailsModel)
    suspend fun setUserLoginPassword(userDetailsModel: UserDetailsModel)
    suspend fun getUserDetails():ActionResult<UserDetailsModel>

}