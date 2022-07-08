package com.ml.truckingandconstructionwork.data.repositoryImpl

import com.google.firebase.firestore.DocumentChange
import com.google.firebase.firestore.QuerySnapshot
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.firestore.ktx.toObject
import com.google.firebase.ktx.Firebase
import com.ml.truckingandconstructionwork.core.ActionResult
import com.ml.truckingandconstructionwork.core.CallException
import com.ml.truckingandconstructionwork.data.models.add_work.OfferModel
import com.ml.truckingandconstructionwork.data.models.registration.UserDetailsModel
import com.ml.truckingandconstructionwork.data.repositoryInterface.OfferRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class OfferRepositoryImpl() : OfferRepository {
    private val db = Firebase.firestore
    private var snapshotSuccess: ActionResult<OfferModel>? = null

    override suspend fun setOffer(offer: OfferModel) {
        withContext(Dispatchers.IO) {
            db.collection("offers")
                .document("${offer.id}")
                .set(mapOf("${offer.id}" to offer))

        }
    }

    override suspend fun getOffers(): ActionResult<List<OfferModel>> {
        var listOffers = mutableMapOf<String,OfferModel>()
        withContext(Dispatchers.IO) {
            db.collection("offers")
                .addSnapshotListener { snapshot, exception ->
                    if (exception != null) {
                        ActionResult.Error(
                            CallException(
                                1001,
                                exception.message ?: "error getData"
                            )
                        )
                    }
                    snapshot?.let {
                        for (change in snapshot.documentChanges) {
                            when (change.type) {
                                DocumentChange.Type.ADDED,
                                DocumentChange.Type.MODIFIED -> {

                                    val liveOffer = change.document.toObject<OfferModel>()
//seeeee
                                      //  listOffers[liveOffer.id] = liveOffer



                                    DocumentChange.Type.REMOVED
                                }
                            }
                        }
                    }
                }
        }
        return if (listOffers.isNotEmpty()) {
            ActionResult.Success(listOffers.values ) as ActionResult<List<OfferModel>>
        }else{
            ActionResult.Error(CallException(100,"null"))
        }
    }
}