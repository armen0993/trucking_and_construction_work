package com.ml.truckingandconstructionwork.presentation.ui.settings.change_password

import androidx.lifecycle.viewModelScope
import com.ml.truckingandconstructionwork.core.ActionResult
import com.ml.truckingandconstructionwork.domain.interactor.ChangePasswordInteractor
import com.ml.truckingandconstructionwork.domain.models.registration.UserDetails
import com.ml.truckingandconstructionwork.presentation.base.BaseViewModel
import com.ml.truckingandconstructionwork.presentation.custom_view.EmptyView
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch

class ChangePasswordViewModel(private val changePasswordInteractor: ChangePasswordInteractor):BaseViewModel() {
    private val _showProgressBar: MutableSharedFlow<EmptyView.State> by lazy { MutableSharedFlow() }
    val showProgressBar = _showProgressBar.asSharedFlow()

    private val _success: MutableSharedFlow<Boolean> by lazy { MutableSharedFlow() }
    val success = _success.asSharedFlow()

    fun changePassword(userDetails: UserDetails,newPassword:String){
        viewModelScope.launch {
            _showProgressBar.emit(EmptyView.State.LOADING)
            when(val result =changePasswordInteractor(userDetails,newPassword) ){
                is ActionResult.Success->{
                    _success.emit(result.data)
                }
            }
            _showProgressBar.emit(EmptyView.State.HIDE)
        }
    }
}