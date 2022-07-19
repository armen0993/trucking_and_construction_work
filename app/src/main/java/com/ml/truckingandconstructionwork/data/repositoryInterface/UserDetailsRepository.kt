package com.ml.truckingandconstructionwork.data.repositoryInterface

import com.ml.truckingandconstructionwork.core.ActionResult
import com.ml.truckingandconstructionwork.data.models.registration.UserDetailsModel
import com.ml.truckingandconstructionwork.domain.models.registration.UserDetails


interface UserDetailsRepository {
    suspend fun setUserDetails(userDetailsModel: UserDetailsModel)
    suspend fun editUserDetails(userDetailsModel: UserDetailsModel)
    suspend fun changePassword(userDetailsModel: UserDetailsModel,newPassword:String)
    suspend fun setUserLoginPassword(userDetailsModel: UserDetailsModel)
    suspend fun getUserDetails():ActionResult<List<UserDetailsModel>>
    suspend fun getUser(userId:String):ActionResult<UserDetailsModel>
    suspend fun setUserDetailInSharedPref(userDetails: UserDetails)
    suspend fun getUserDetailInSharedPref(): UserDetails
    suspend fun getSkippedTypeInSharedPref(): Boolean
    suspend fun setSkippedTypeInSharedPref(boolean: Boolean)
    suspend fun logOut()


}