package com.ml.truckingandconstructionwork.core

sealed class ActionResult<out S> {
    data class Success<S>(val data: S) : ActionResult<S>()
    data class Error(val errors: CallException<RequestError>) : ActionResult<Nothing>()
}