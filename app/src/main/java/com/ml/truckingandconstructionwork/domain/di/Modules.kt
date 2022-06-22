package com.ml.truckingandconstructionwork.domain.di

import com.ml.truckingandconstructionwork.domain.interactor.GetUserDetailsInteractor
import com.ml.truckingandconstructionwork.domain.interactor.SetUserDetailsInteractor
import com.ml.truckingandconstructionwork.domain.interactor.SetUserLoginPasswordInteractor
import com.ml.truckingandconstructionwork.domain.use_case.GetUserDetailsUseCase
import com.ml.truckingandconstructionwork.domain.use_case.SetUserDetailsUseCase
import com.ml.truckingandconstructionwork.domain.use_case.SetUserLoginPasswordUseCase
import org.koin.dsl.module

val interactorModule = module {
    factory<SetUserDetailsInteractor> { SetUserDetailsUseCase(get()) }
    factory<GetUserDetailsInteractor> { GetUserDetailsUseCase(get()) }
    factory<SetUserLoginPasswordInteractor> { SetUserLoginPasswordUseCase(get()) }
}