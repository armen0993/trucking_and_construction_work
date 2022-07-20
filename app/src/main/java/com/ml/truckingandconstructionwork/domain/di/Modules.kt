package com.ml.truckingandconstructionwork.domain.di

import com.ml.truckingandconstructionwork.domain.interactor.ChangePasswordInteractor
import com.ml.truckingandconstructionwork.domain.interactor.EditUserDetailsInteractor
import com.ml.truckingandconstructionwork.domain.interactor.GetUserInteractor
import com.ml.truckingandconstructionwork.domain.interactor.LogOutInteractor
import com.ml.truckingandconstructionwork.domain.interactor.add_work.SetOfferInteractor
import com.ml.truckingandconstructionwork.domain.interactor.log_in.CheckUserDetailsInteractor
import com.ml.truckingandconstructionwork.domain.interactor.log_in.SetSkippedTypeInSharedInteractor
import com.ml.truckingandconstructionwork.domain.interactor.offer.GetOffersInteractor
import com.ml.truckingandconstructionwork.domain.interactor.registration.GetUserDetailsInteractor
import com.ml.truckingandconstructionwork.domain.interactor.registration.SaveDataInSharedInteractor
import com.ml.truckingandconstructionwork.domain.interactor.registration.SetUserDetailsInteractor
import com.ml.truckingandconstructionwork.domain.interactor.registration.SetUserLoginPasswordInteractor
import com.ml.truckingandconstructionwork.domain.interactor.special_equipment.GetSpecialEquipmentInteractor
import com.ml.truckingandconstructionwork.domain.interactor.special_equipment.GetUserSpecialEquipmentInteractor
import com.ml.truckingandconstructionwork.domain.interactor.special_equipment.SetSpecialEquipmentInteractor
import com.ml.truckingandconstructionwork.domain.interactor.splash.GetSkippedTypeWithSharedPrefInteractor
import com.ml.truckingandconstructionwork.domain.interactor.splash.GetUserDetailWithSharedPrefInteractor
import com.ml.truckingandconstructionwork.domain.use_case.ChangePasswordUseCase
import com.ml.truckingandconstructionwork.domain.use_case.EditUserDetailsUseCase
import com.ml.truckingandconstructionwork.domain.use_case.GetUserUseCase
import com.ml.truckingandconstructionwork.domain.use_case.LogOutUseCase
import com.ml.truckingandconstructionwork.domain.use_case.add_work.SetOfferUseCase
import com.ml.truckingandconstructionwork.domain.use_case.log_in.CheckUserDetailsUseCase
import com.ml.truckingandconstructionwork.domain.use_case.log_in.SetSkippedTypeInSharedUseCase
import com.ml.truckingandconstructionwork.domain.use_case.offers.GetOffersUseCase
import com.ml.truckingandconstructionwork.domain.use_case.registration.GetUserDetailsUseCase
import com.ml.truckingandconstructionwork.domain.use_case.registration.SaveDataInSharedUseCase
import com.ml.truckingandconstructionwork.domain.use_case.registration.SetUserDetailsUseCase
import com.ml.truckingandconstructionwork.domain.use_case.registration.SetUserLoginPasswordUseCase
import com.ml.truckingandconstructionwork.domain.use_case.special_equipment.GetSpecialEquipmentUseCase
import com.ml.truckingandconstructionwork.domain.use_case.special_equipment.GetUserSpecialEquipmentUseCase
import com.ml.truckingandconstructionwork.domain.use_case.special_equipment.SetSpecialEquipmentUseCase
import com.ml.truckingandconstructionwork.domain.use_case.splash.GetSkippedTypeWithSharedPrefUseCase
import com.ml.truckingandconstructionwork.domain.use_case.splash.GetUserDetailWithSharedPrefUseCase
import org.koin.dsl.module

val interactorModule = module {
    factory<SetUserDetailsInteractor> { SetUserDetailsUseCase(get()) }
    factory<GetUserDetailsInteractor> { GetUserDetailsUseCase(get()) }
    factory<SetUserLoginPasswordInteractor> { SetUserLoginPasswordUseCase(get()) }
    factory<SetOfferInteractor> { SetOfferUseCase(get()) }
    factory<GetOffersInteractor> { GetOffersUseCase(get()) }
    factory<SaveDataInSharedInteractor> { SaveDataInSharedUseCase(get()) }
    factory<CheckUserDetailsInteractor> { CheckUserDetailsUseCase(get()) }
    factory<GetUserDetailWithSharedPrefInteractor> { GetUserDetailWithSharedPrefUseCase(get()) }
    factory<GetUserInteractor> { GetUserUseCase(get()) }
    factory<LogOutInteractor> { LogOutUseCase(get()) }
    factory<GetSkippedTypeWithSharedPrefInteractor> { GetSkippedTypeWithSharedPrefUseCase(get()) }
    factory<SetSkippedTypeInSharedInteractor> { SetSkippedTypeInSharedUseCase(get()) }
    factory<EditUserDetailsInteractor> { EditUserDetailsUseCase(get()) }
    factory<ChangePasswordInteractor> { ChangePasswordUseCase(get()) }
    factory<SetSpecialEquipmentInteractor> { SetSpecialEquipmentUseCase(get()) }
    factory<GetUserSpecialEquipmentInteractor> { GetUserSpecialEquipmentUseCase(get()) }
    factory<GetSpecialEquipmentInteractor> { GetSpecialEquipmentUseCase(get()) }

}