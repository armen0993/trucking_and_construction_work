package com.ml.truckingandconstructionwork.data.di

import com.ml.truckingandconstructionwork.data.repositoryImpl.OfferRepositoryImpl
import com.ml.truckingandconstructionwork.data.repositoryImpl.UserDetailsRepositoryImpl
import com.ml.truckingandconstructionwork.data.repositoryInterface.OfferRepository
import com.ml.truckingandconstructionwork.data.repositoryInterface.UserDetailsRepository
import org.koin.dsl.module

val repositoryModule = module {
    factory<UserDetailsRepository> { UserDetailsRepositoryImpl() }
    factory<OfferRepository> { OfferRepositoryImpl() }
}