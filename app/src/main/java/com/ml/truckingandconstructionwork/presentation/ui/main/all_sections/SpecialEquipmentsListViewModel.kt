package com.ml.truckingandconstructionwork.presentation.ui.main.all_sections

import androidx.lifecycle.viewModelScope
import com.ml.truckingandconstructionwork.core.ActionResult
import com.ml.truckingandconstructionwork.domain.interactor.GetUserInteractor
import com.ml.truckingandconstructionwork.domain.interactor.special_equipment.FilterSpecialEquipmentInteractor
import com.ml.truckingandconstructionwork.domain.interactor.special_equipment.GetSpecialEquipmentInteractor
import com.ml.truckingandconstructionwork.domain.models.registration.UserDetails
import com.ml.truckingandconstructionwork.domain.models.special_equipment.SpecialEquipment
import com.ml.truckingandconstructionwork.presentation.base.BaseViewModel
import com.ml.truckingandconstructionwork.presentation.base.model.BaseModelAdapter
import com.ml.truckingandconstructionwork.presentation.custom_view.EmptyView
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch

class SpecialEquipmentsListViewModel(
    private val getSpecialEquipmentInteractor: GetSpecialEquipmentInteractor,
    private val getUserInteractor: GetUserInteractor,
    private val filterSpecialEquipmentInteractor: FilterSpecialEquipmentInteractor
) : BaseViewModel() {
    private val _showProgressBar: MutableSharedFlow<EmptyView.State> by lazy { MutableSharedFlow() }
    val showProgressBar = _showProgressBar.asSharedFlow()

    private val _showProgressBarAlert: MutableSharedFlow<EmptyView.State> by lazy { MutableSharedFlow() }
    val showProgressBarAlert = _showProgressBarAlert.asSharedFlow()

    private val _listSpecialEquipment: MutableSharedFlow<List<BaseModelAdapter<String>>> by lazy { MutableSharedFlow() }
    val listSpecialEquipment = _listSpecialEquipment.asSharedFlow()

    private val _listFilterSpecialEquipment: MutableSharedFlow<List<BaseModelAdapter<String>>> by lazy { MutableSharedFlow() }
    val listFilterSpecialEquipment = _listFilterSpecialEquipment.asSharedFlow()

    private val _userDetails: MutableSharedFlow<UserDetails> by lazy { MutableSharedFlow() }
    val userDetails = _userDetails.asSharedFlow()

    private var list = listOf<BaseModelAdapter<String>>()


    fun getSpecialEquipmentList(type: String) {
        viewModelScope.launch {
            _showProgressBar.emit(EmptyView.State.LOADING)

            when (val result = getSpecialEquipmentInteractor(type)) {
                is ActionResult.Success -> {
                    list = result.data
                    _listSpecialEquipment.emit(result.data)
                }
            }

            if (list.size<=1) {
                _showProgressBar.emit(EmptyView.State.EMPTY)
            } else {
                _showProgressBar.emit(EmptyView.State.HIDE)
            }

        }
    }

    fun getUserDetails(userId: String) {
        viewModelScope.launch {
            _showProgressBarAlert.emit(EmptyView.State.LOADING)


            when (val result = getUserInteractor(userId)) {
                is ActionResult.Success -> {

                    _userDetails.emit(result.data)
                }
            }
            _showProgressBarAlert.emit(EmptyView.State.HIDE)


        }
    }


    fun filterEquipmentType(filter: String) {
        viewModelScope.launch {
            _showProgressBar.emit(EmptyView.State.LOADING)


            when (val result = filterSpecialEquipmentInteractor(filter)) {
                is ActionResult.Success -> {

                    _listFilterSpecialEquipment.emit(result.data)
                }
            }
            _showProgressBar.emit(EmptyView.State.HIDE)


        }
    }
}