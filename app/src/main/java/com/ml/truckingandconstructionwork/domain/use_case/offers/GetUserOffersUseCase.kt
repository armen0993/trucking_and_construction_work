package com.ml.truckingandconstructionwork.domain.use_case.offers

import com.ml.truckingandconstructionwork.core.ActionResult
import com.ml.truckingandconstructionwork.core.CallException
import com.ml.truckingandconstructionwork.data.repositoryInterface.OfferRepository
import com.ml.truckingandconstructionwork.domain.interactor.offer.GetUserOffersInteractor
import com.ml.truckingandconstructionwork.domain.models.add_work.Offer
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class GetUserOffersUseCase(private val offerRepository: OfferRepository):GetUserOffersInteractor {
    override suspend fun invoke(userId: String): ActionResult<List<Offer>> =
        withContext(Dispatchers.IO){
            val list = mutableListOf<Offer>()
            when(val apiData = offerRepository.getOffers()){
                is ActionResult.Success->{
                    apiData.data.let { listData->
                        listData.forEach {
                            if (it.userId == userId){
                                list.add( Offer.from(it) )
                            }
                        }
                        ActionResult.Success(list)
                    }
                }
                is ActionResult.Error->{
                    ActionResult.Error(CallException(1001,"error"))
                }
            }
        }
}