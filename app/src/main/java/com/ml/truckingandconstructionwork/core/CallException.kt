package com.ml.truckingandconstructionwork.core

data class CallException<RequestError>(
    val errorCode: Int,
    val errorMessage: String? = null,
    val errorBody: RequestError? = null
)