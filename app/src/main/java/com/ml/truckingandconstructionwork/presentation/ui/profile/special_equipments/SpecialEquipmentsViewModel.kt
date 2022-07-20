package com.ml.truckingandconstructionwork.presentation.ui.profile.special_equipments

import androidx.lifecycle.viewModelScope
import com.ml.truckingandconstructionwork.core.ActionResult
import com.ml.truckingandconstructionwork.domain.interactor.special_equipment.GetUserSpecialEquipmentInteractor
import com.ml.truckingandconstructionwork.domain.models.special_equipment.SpecialEquipment
import com.ml.truckingandconstructionwork.presentation.base.BaseViewModel
import com.ml.truckingandconstructionwork.presentation.custom_view.EmptyView
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch

class SpecialEquipmentsViewModel(
    private val getUserSpecialEquipmentInteractor: GetUserSpecialEquipmentInteractor
):BaseViewModel() {
    private val _showProgressBar: MutableSharedFlow<EmptyView.State> by lazy { MutableSharedFlow() }
    val showProgressBar = _showProgressBar.asSharedFlow()

    private val _listSpecialEquipment: MutableSharedFlow<List<SpecialEquipment>> by lazy { MutableSharedFlow() }
    val listSpecialEquipment = _listSpecialEquipment.asSharedFlow()

    private var list = listOf<SpecialEquipment>()

    fun getSpecialEquipmentList(userId:String) {
        viewModelScope.launch {
            _showProgressBar.emit(EmptyView.State.LOADING)


            when (val result = getUserSpecialEquipmentInteractor(userId)) {
                is ActionResult.Success -> {
                    list = result.data
                    _listSpecialEquipment.emit(list)
                }
            }


            if (list.isEmpty()) {
                _showProgressBar.emit(EmptyView.State.EMPTY)
            } else {
                _showProgressBar.emit(EmptyView.State.HIDE)
            }

        }
    }
}