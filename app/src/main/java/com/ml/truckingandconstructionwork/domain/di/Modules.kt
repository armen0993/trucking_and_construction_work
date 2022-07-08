package com.ml.truckingandconstructionwork.domain.di

import com.ml.truckingandconstructionwork.domain.interactor.add_work.SetOfferInteractor
import com.ml.truckingandconstructionwork.domain.interactor.offer.GetOffersInteractor
import com.ml.truckingandconstructionwork.domain.interactor.registration.GetUserDetailsInteractor
import com.ml.truckingandconstructionwork.domain.interactor.registration.SetUserDetailsInteractor
import com.ml.truckingandconstructionwork.domain.interactor.registration.SetUserLoginPasswordInteractor
import com.ml.truckingandconstructionwork.domain.use_case.add_work.SetOfferUseCase
import com.ml.truckingandconstructionwork.domain.use_case.offers.GetOffersUseCase
import com.ml.truckingandconstructionwork.domain.use_case.registration.GetUserDetailsUseCase
import com.ml.truckingandconstructionwork.domain.use_case.registration.SetUserDetailsUseCase
import com.ml.truckingandconstructionwork.domain.use_case.registration.SetUserLoginPasswordUseCase
import org.koin.dsl.module

val interactorModule = module {
    factory<SetUserDetailsInteractor> { SetUserDetailsUseCase(get()) }
    factory<GetUserDetailsInteractor> { GetUserDetailsUseCase(get()) }
    factory<SetUserLoginPasswordInteractor> { SetUserLoginPasswordUseCase(get()) }
    factory<SetOfferInteractor> { SetOfferUseCase(get()) }
    factory<GetOffersInteractor> { GetOffersUseCase(get()) }

}