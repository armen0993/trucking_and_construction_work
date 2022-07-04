package com.ml.truckingandconstructionwork.domain.models

import com.ml.truckingandconstructionwork.presentation.base.model.DiffUtilModel

data class OffersListModel(
    override val id: Int,
    val equipmentType:String,
    val description:String
    ): DiffUtilModel<Int>()
