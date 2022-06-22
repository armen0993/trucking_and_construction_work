package com.ml.truckingandconstructionwork.core

import com.google.gson.annotations.SerializedName


open class RequestError

data class ErrorNotFound(
    @SerializedName(value = "detail", alternate = ["Detail"])
    val detail: String?
): RequestError()

data class ErrorBody(
    @SerializedName(value = "errors", alternate = ["Errors"])
    val errors: List<Error>?
): RequestError()

data class Error(
    @SerializedName(value = "loc", alternate = ["Loc"])
    val loc: List<String>,
    @SerializedName(value = "msg", alternate = ["Msg"])
    val msg : String?,
    @SerializedName(value = "type", alternate = ["Type"])
    val type: String?
)
