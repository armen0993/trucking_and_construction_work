package com.ml.truckingandconstructionwork.data.repositoryImpl

import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.firestore.ktx.toObject
import com.google.firebase.ktx.Firebase
import com.ml.truckingandconstructionwork.core.ActionResult
import com.ml.truckingandconstructionwork.core.CallException
import com.ml.truckingandconstructionwork.data.models.registration.UserDetailsModel
import com.ml.truckingandconstructionwork.data.repositoryInterface.UserDetailsRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class UserDetailsRepositoryImpl(
) : UserDetailsRepository {
    private val db = Firebase.firestore
    private var snapshotSuccess: ActionResult<UserDetailsModel>? = null



    override suspend fun setUserLoginPassword(userDetailsModel: UserDetailsModel) {
        withContext(Dispatchers.IO) {
            db.collection("user_details")
                .document("${userDetailsModel.id}")
                .set(userDetailsModel)

        }
    }

    override suspend fun setUserDetails(userDetailsModel: UserDetailsModel) {
        withContext(Dispatchers.IO) {
            db.collection("user_details")
                .document("users_document")
                .update(mapOf("${userDetailsModel.id}.login" to userDetailsModel.login,"${userDetailsModel.id}.password" to userDetailsModel.password))
        }
    }

    override suspend fun getUserDetails(): ActionResult<UserDetailsModel> {
        withContext(Dispatchers.IO) {
            db.collection("user_details")
                .document("users_document")
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
                    snapshot?.let {document->

                        document.toObject<UserDetailsModel>().let { doc->
                            doc?.let { data->
//                                snapshotSuccess = if (data?.phoneNumber.isNullOrEmpty()){
//                                    ActionResult.Error(CallException(100,"null data"))
//                                } else{
//                                    ActionResult.Success(data!!)
//                                }
                                snapshotSuccess = ActionResult.Success(data)
                            }


                        }


                    }
                }
        }
        return if (snapshotSuccess!=null){
            snapshotSuccess as ActionResult<UserDetailsModel>
        }else{
            ActionResult.Error(CallException(100,"null"))
        }
    }



}