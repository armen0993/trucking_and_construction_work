package com.ml.truckingandconstructionwork.data.repositoryImpl

import android.util.Log
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
import com.ml.truckingandconstructionwork.data.utils.Constants.OFFERS
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.withContext

class OfferRepositoryImpl() : OfferRepository {
    private val db = Firebase.firestore


    override suspend fun setOffer(offer: OfferModel) {
        withContext(Dispatchers.IO) {
            db.collection(OFFERS)
                .document("${offer.id}")
                .set(offer)

        }
    }

    override suspend fun getOffers(): ActionResult<List<OfferModel>> {
        val offersMap = mutableMapOf<String, OfferModel>()
        val list = mutableListOf<OfferModel>()

        return withContext(Dispatchers.IO) {
            db.collection(OFFERS)
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
                        for (change in it.documentChanges) {

                            change.document.toObject(OfferModel::class.java).let { doc ->
                                offersMap[doc.id.toString()] = doc
                                list.add(doc)
                            }
                        }
                        ActionResult.Success(list)
                    }


                }


            withContext(Dispatchers.Default){
                delay(1000)
                ActionResult.Success(list)
            }



        }
    }
}