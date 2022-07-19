package com.ml.truckingandconstructionwork.data.repositoryImpl

import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.firestore.ktx.toObject
import com.google.firebase.ktx.Firebase
import com.ml.truckingandconstructionwork.core.ActionResult
import com.ml.truckingandconstructionwork.core.CallException
import com.ml.truckingandconstructionwork.data.app_service.PreferenceService
import com.ml.truckingandconstructionwork.data.models.registration.UserDetailsModel
import com.ml.truckingandconstructionwork.data.repositoryInterface.UserDetailsRepository
import com.ml.truckingandconstructionwork.domain.models.registration.UserDetails
import com.ml.truckingandconstructionwork.presentation.utils.Constants.USER_DETAILS_COLLECTION
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.withContext

class UserDetailsRepositoryImpl(
    private val sharedPreferenceService: PreferenceService
) : UserDetailsRepository {
    private val db = Firebase.firestore
    private var snapshotSuccess: ActionResult<UserDetailsModel>? = null


    override suspend fun setUserLoginPassword(userDetailsModel: UserDetailsModel) {
        withContext(Dispatchers.IO) {
            db.collection(USER_DETAILS_COLLECTION)
                .document("${userDetailsModel.id}")
                .set(userDetailsModel)

        }
    }

    override suspend fun setUserDetails(userDetailsModel: UserDetailsModel) {
        withContext(Dispatchers.IO) {
            db.collection(USER_DETAILS_COLLECTION)
                .document("${userDetailsModel.id}")
                .update(
                    "name", userDetailsModel.name,
                    "surname", userDetailsModel.surname,
                    "city", userDetailsModel.city,
                    "clientType", userDetailsModel.clientType,
                    "dataOfBirth", userDetailsModel.dataOfBirth,
                    "email", userDetailsModel.email,
                    "gender", userDetailsModel.gender,
                    "phoneNumber", userDetailsModel.phoneNumber
                )
        }
    }

    override suspend fun editUserDetails(userDetailsModel: UserDetailsModel) {
        withContext(Dispatchers.IO) {
            db.collection(USER_DETAILS_COLLECTION)
                .document("${userDetailsModel.id}")
                .update(
                    "name", userDetailsModel.name,
                    "surname", userDetailsModel.surname,
                    "city", userDetailsModel.city,
                    "dataOfBirth", userDetailsModel.dataOfBirth,
                )
        }
    }

    override suspend fun changePassword(userDetailsModel: UserDetailsModel,newPassword:String) {
        withContext(Dispatchers.IO) {
            db.collection(USER_DETAILS_COLLECTION)
                .document("${userDetailsModel.id}")
                .update(
                    "password", newPassword
                )
        }
    }

    override suspend fun getUserDetails(): ActionResult<List<UserDetailsModel>> {
        val usersMap = mutableMapOf<String, UserDetailsModel>()
        val list = mutableListOf<UserDetailsModel>()
        return withContext(Dispatchers.IO) {
            db.collection(USER_DETAILS_COLLECTION)
                .addSnapshotListener { snapshot, exception ->
                    if (exception != null) {
                        ActionResult.Error(
                            CallException(
                                1001,
                                exception.message ?: "error getData"
                            )
                        )
                        return@addSnapshotListener
                    }
                    snapshot?.let { document ->
                        for (change in document.documentChanges) {
                            change.document.toObject<UserDetailsModel>().let { doc ->

                                usersMap[doc.id.toString()] = doc

                                list.add(doc)
                            }


                        }
                        //   snapshotSuccess = ActionResult.Success(list)


                    }
                }

            withContext(Dispatchers.Default) {
                delay(2000)
                return@withContext ActionResult.Success(list)
            }
        }

    }

    override suspend fun getUser(userId: String): ActionResult<UserDetailsModel> {
        return withContext(Dispatchers.IO) {
            var user = UserDetailsModel()
            db.collection(USER_DETAILS_COLLECTION)
                .document(userId)
                .addSnapshotListener { snapshot, exception ->
                    if (exception != null) {
                        ActionResult.Error(
                            CallException(
                                1001,
                                exception.message ?: "error getData"
                            )
                        )
                        return@addSnapshotListener
                    }
                    snapshot?.let { document ->

                        user = document.toObject<UserDetailsModel>()!!


                        //   snapshotSuccess = ActionResult.Success(list)


                    }
                }
            withContext(Dispatchers.Default) {
                delay(2000)
                return@withContext ActionResult.Success(user)
            }
        }
    }

    override suspend fun setUserDetailInSharedPref(userDetails: UserDetails) {
        sharedPreferenceService.setUserDetails(userDetails)
    }

    override suspend fun getUserDetailInSharedPref(): UserDetails {
        return sharedPreferenceService.getUserDetails()
    }

    override suspend fun getSkippedTypeInSharedPref(): Boolean {
        return sharedPreferenceService.getSkipped()
    }

    override suspend fun setSkippedTypeInSharedPref(boolean: Boolean) {
        sharedPreferenceService.setSkipped(boolean)
    }

    override suspend fun logOut() {
        sharedPreferenceService.clearUserDetails()
        sharedPreferenceService.setSkipped(false)
    }


}