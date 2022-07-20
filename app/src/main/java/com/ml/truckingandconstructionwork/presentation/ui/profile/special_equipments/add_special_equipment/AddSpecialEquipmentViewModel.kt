package com.ml.truckingandconstructionwork.presentation.ui.profile.special_equipments.add_special_equipment

import androidx.lifecycle.viewModelScope
import com.ml.truckingandconstructionwork.core.ActionResult
import com.ml.truckingandconstructionwork.domain.interactor.special_equipment.SetSpecialEquipmentInteractor
import com.ml.truckingandconstructionwork.domain.models.special_equipment.SpecialEquipment
import com.ml.truckingandconstructionwork.presentation.base.BaseViewModel
import com.ml.truckingandconstructionwork.presentation.custom_view.EmptyView
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch

class AddSpecialEquipmentViewModel(
    private val setSpecialEquipmentInteractor: SetSpecialEquipmentInteractor
):BaseViewModel() {

    private val _showProgressBar: MutableSharedFlow<EmptyView.State> by lazy { MutableSharedFlow() }
    val showProgressBar = _showProgressBar.asSharedFlow()

    private val _success: MutableSharedFlow<Boolean> by lazy { MutableSharedFlow() }
    val success = _success.asSharedFlow()

    fun setSpecialEquipment(specialEquipment: SpecialEquipment){
        viewModelScope.launch {
            _showProgressBar.emit(EmptyView.State.LOADING)

            when (val result = setSpecialEquipmentInteractor(specialEquipment)) {
                is ActionResult.Success -> {
                    delay(2000)
                    _success.emit(result.data)
                }
            }
            _showProgressBar.emit(EmptyView.State.HIDE)

        }
    }
}