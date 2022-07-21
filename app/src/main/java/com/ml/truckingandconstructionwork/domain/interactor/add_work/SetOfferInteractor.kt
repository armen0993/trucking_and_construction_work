package com.ml.truckingandconstructionwork.domain.interactor.add_work

import com.ml.truckingandconstructionwork.core.ActionResult
import com.ml.truckingandconstructionwork.domain.models.add_work.Offer

interface SetOfferInteractor {
    suspend operator fun invoke(offerModel: Offer): ActionResult<Boolean>
}