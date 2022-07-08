package com.ml.truckingandconstructionwork.presentation.ui.registration.personal_details

import androidx.lifecycle.viewModelScope
import com.ml.truckingandconstructionwork.core.ActionResult
import com.ml.truckingandconstructionwork.domain.interactor.registration.SetUserDetailsInteractor
import com.ml.truckingandconstructionwork.domain.interactor.registration.GetUserDetailsInteractor
import com.ml.truckingandconstructionwork.domain.models.registration.UserDetails
import com.ml.truckingandconstructionwork.presentation.base.BaseViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch

class PersonalDetailsViewModel(
    private val setUserDetailsInteractor: SetUserDetailsInteractor,
    private val getUserDetailsInteractor: GetUserDetailsInteractor
) : BaseViewModel() {

    private val _showProgressBar: MutableSharedFlow<Boolean> by lazy { MutableSharedFlow() }
    val showProgressBar = _showProgressBar.asSharedFlow()

    private val _startAlertConfirmPhoneNumber: MutableSharedFlow<Boolean> by lazy { MutableSharedFlow() }
    val startAlertConfirmPhoneNumber = _startAlertConfirmPhoneNumber.asSharedFlow()


    fun setUserDetails(userDetails: UserDetails) {
        viewModelScope.launch {
            _showProgressBar.emit(true)
            setUserDetailsInteractor(userDetails)
            _startAlertConfirmPhoneNumber.emit(true)
            _showProgressBar.emit(false)
        }
    }

     fun getUserDetails() {
        viewModelScope.launch {
            _showProgressBar.emit(true)
            when (val result = getUserDetailsInteractor()) {
                is ActionResult.Success -> {
                    _startAlertConfirmPhoneNumber.emit(true)
                }
                is ActionResult.Error->{
                    _startAlertConfirmPhoneNumber.emit(false)
                }
            }
            _showProgressBar.emit(false)
        }
    }
}