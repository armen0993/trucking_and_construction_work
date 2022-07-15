package com.ml.truckingandconstructionwork.data.repositoryInterface

import com.ml.truckingandconstructionwork.core.ActionResult
import com.ml.truckingandconstructionwork.data.models.registration.UserDetailsModel
import com.ml.truckingandconstructionwork.domain.models.registration.UserDetails


interface UserDetailsRepository {
    suspend fun setUserDetails(userDetailsModel: UserDetailsModel)
    suspend fun setUserLoginPassword(userDetailsModel: UserDetailsModel)
    suspend fun getUserDetails():ActionResult<List<UserDetailsModel>>
    suspend fun getUser(userId:String):ActionResult<UserDetailsModel>
    suspend fun setUserDetailInSharedPref(userDetails: UserDetails)
    suspend fun getUserDetailInSharedPref(): UserDetails

}