package com.ml.truckingandconstructionwork.domain.use_case.add_work

import com.ml.truckingandconstructionwork.core.ActionResult
import com.ml.truckingandconstructionwork.data.repositoryInterface.OfferRepository
import com.ml.truckingandconstructionwork.domain.interactor.add_work.SetOfferInteractor
import com.ml.truckingandconstructionwork.domain.models.add_work.Offer
import com.ml.truckingandconstructionwork.domain.utils.toRequest
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class SetOfferUseCase(private val offerRepository: OfferRepository):SetOfferInteractor {
    override suspend fun invoke(offer: Offer) =
        withContext(Dispatchers.IO) {
           offerRepository.setOffer(offer.toRequest() )
        }

}