package com.ml.truckingandconstructionwork.domain.models

import com.ml.truckingandconstructionwork.presentation.base.model.BaseModelAdapter

data class MessageModel(
    val message:String,
    override val id: String
): BaseModelAdapter<String>()
