package com.ml.truckingandconstructionwork.data.repositoryInterface

import com.ml.truckingandconstructionwork.core.ActionResult
import com.ml.truckingandconstructionwork.data.models.add_work.OfferModel

interface OfferRepository {
    suspend fun setOffer(offer: OfferModel):ActionResult<Boolean>
    suspend fun getOffers():ActionResult<List<OfferModel>>
}