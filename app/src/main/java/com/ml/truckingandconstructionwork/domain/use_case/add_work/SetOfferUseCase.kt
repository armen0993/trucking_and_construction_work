package com.ml.truckingandconstructionwork.domain.use_case.add_work

import com.ml.truckingandconstructionwork.core.ActionResult
import com.ml.truckingandconstructionwork.core.CallException
import com.ml.truckingandconstructionwork.data.repositoryInterface.OfferRepository
import com.ml.truckingandconstructionwork.domain.interactor.add_work.SetOfferInteractor
import com.ml.truckingandconstructionwork.domain.models.add_work.Offer
import com.ml.truckingandconstructionwork.domain.utils.toRequest
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class SetOfferUseCase(private val offerRepository: OfferRepository):SetOfferInteractor {
    override suspend fun invoke(offer: Offer): ActionResult<Boolean> =
        withContext(Dispatchers.IO) {

            when (val apiData =
               offerRepository.setOffer(offer.toRequest() )) {
                is ActionResult.Success -> {
                    apiData.data.let {
                        ActionResult.Success(it)
                    }
                }
                is ActionResult.Error -> {
                    ActionResult.Error(CallException(1001, "error use case"))
                }
            }
        }

}