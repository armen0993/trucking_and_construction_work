package com.ml.truckingandconstructionwork.domain.interactor.offer

import com.ml.truckingandconstructionwork.core.ActionResult
import com.ml.truckingandconstructionwork.domain.models.add_work.Offer

interface GetOffersInteractor {
     suspend fun invoke():ActionResult<List<Offer>>
}