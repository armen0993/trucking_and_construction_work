package com.ml.truckingandconstructionwork.presentation.ui.sign_in.registration.create_login_password

import androidx.lifecycle.viewModelScope
import com.ml.truckingandconstructionwork.core.ActionResult
import com.ml.truckingandconstructionwork.domain.interactor.GetUserDetailsInteractor
import com.ml.truckingandconstructionwork.domain.interactor.SetUserLoginPasswordInteractor
import com.ml.truckingandconstructionwork.domain.models.UserDetails
import com.ml.truckingandconstructionwork.presentation.base.BaseViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch

class CreateLoginPasswordViewModel(
    private val setUserLoginPasswordInteractor: SetUserLoginPasswordInteractor,
    private val getUserDetailsInteractor: GetUserDetailsInteractor
):BaseViewModel() {

    private val _showProgressBar: MutableSharedFlow<Boolean> by lazy { MutableSharedFlow() }
    val showProgressBar = _showProgressBar.asSharedFlow()

    private val _startAlertDialog: MutableSharedFlow<Boolean> by lazy { MutableSharedFlow() }
    val startAlertDialog = _startAlertDialog.asSharedFlow()

    fun setUserDetails(userDetails: UserDetails) {
        viewModelScope.launch {
            _showProgressBar.emit(true)
            setUserLoginPasswordInteractor(userDetails)
            _showProgressBar.emit(false)
        }
    }
    fun getUserDetails() {
        viewModelScope.launch {
            _showProgressBar.emit(true)
            when (val result = getUserDetailsInteractor()) {
                is ActionResult.Success -> {
                    _startAlertDialog.emit(true)
                }
                is ActionResult.Error->{
                    _startAlertDialog.emit(false)
                }
            }
            _showProgressBar.emit(false)
        }
    }
}