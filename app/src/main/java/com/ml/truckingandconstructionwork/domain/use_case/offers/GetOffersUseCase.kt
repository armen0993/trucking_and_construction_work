package com.ml.truckingandconstructionwork.domain.use_case.offers

import com.ml.truckingandconstructionwork.core.ActionResult
import com.ml.truckingandconstructionwork.core.CallException
import com.ml.truckingandconstructionwork.data.models.add_work.OfferModel
import com.ml.truckingandconstructionwork.data.repositoryInterface.OfferRepository
import com.ml.truckingandconstructionwork.domain.interactor.offer.GetOffersInteractor
import com.ml.truckingandconstructionwork.domain.models.add_work.Offer
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class GetOffersUseCase(private val offerRepository: OfferRepository):GetOffersInteractor {
    override suspend fun invoke(): ActionResult<List<Offer>> = withContext(Dispatchers.IO) {
        when (val apiData = offerRepository.getOffers()) {
            is ActionResult.Success -> {
                apiData.data.let {
                    ActionResult.Success( it.map { Offer.from(it) })
                }
            }
            is ActionResult.Error->{
                ActionResult.Error(CallException(1001,"error"))
            }

        }
    }
}