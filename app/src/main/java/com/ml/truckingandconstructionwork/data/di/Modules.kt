package com.ml.truckingandconstructionwork.data.di

import com.ml.truckingandconstructionwork.data.app_service.PreferenceService
import com.ml.truckingandconstructionwork.data.app_service.PreferenceServiceImpl
import com.ml.truckingandconstructionwork.data.repositoryImpl.OfferRepositoryImpl
import com.ml.truckingandconstructionwork.data.repositoryImpl.SpecialEquipmentRepositoryImpl
import com.ml.truckingandconstructionwork.data.repositoryImpl.UserDetailsRepositoryImpl
import com.ml.truckingandconstructionwork.data.repositoryInterface.OfferRepository
import com.ml.truckingandconstructionwork.data.repositoryInterface.SpecialEquipmentRepository
import com.ml.truckingandconstructionwork.data.repositoryInterface.UserDetailsRepository
import org.koin.dsl.module

val repositoryModule = module {
    single<PreferenceService> { PreferenceServiceImpl(get()) }
    factory<UserDetailsRepository> { UserDetailsRepositoryImpl(get()) }
    factory<OfferRepository> { OfferRepositoryImpl() }
    factory<SpecialEquipmentRepository> { SpecialEquipmentRepositoryImpl() }
}