package com.ml.truckingandconstructionwork.domain.models

import com.ml.truckingandconstructionwork.presentation.base.model.BaseModelAdapter

data class SearchModel(
    override val id: String
):BaseModelAdapter<String>()
