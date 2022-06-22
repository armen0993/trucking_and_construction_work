package com.ml.truckingandconstructionwork.data.di

import com.ml.truckingandconstructionwork.data.api_service.UserDetailsDataSourceImpl
import com.ml.truckingandconstructionwork.data.data_source_interface.UserDetailsDataSource
import com.ml.truckingandconstructionwork.data.repositoryImpl.UserDetailsRepositoryImpl
import com.ml.truckingandconstructionwork.data.repositoryInterface.UserDetailsRepository
import org.koin.dsl.module


val dataSourceModule = module {
factory < UserDetailsDataSource >{UserDetailsDataSourceImpl()}
}

val repositoryModule = module {
    factory<UserDetailsRepository> { UserDetailsRepositoryImpl(get()) }
}