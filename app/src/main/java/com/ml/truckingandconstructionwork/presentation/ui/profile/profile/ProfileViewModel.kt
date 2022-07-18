package com.ml.truckingandconstructionwork.presentation.ui.profile.profile
import androidx.lifecycle.viewModelScope
import com.ml.truckingandconstructionwork.core.ActionResult
import com.ml.truckingandconstructionwork.domain.interactor.GetUserInteractor
import com.ml.truckingandconstructionwork.domain.models.registration.UserDetails
import com.ml.truckingandconstructionwork.presentation.base.BaseViewModel
import com.ml.truckingandconstructionwork.presentation.custom_view.EmptyView
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch

class ProfileViewModel(private val getUserInteractor: GetUserInteractor):BaseViewModel() {

    private val _showProgressBar: MutableSharedFlow<EmptyView.State> by lazy { MutableSharedFlow() }
    val showProgressBar = _showProgressBar.asSharedFlow()

    private val _userDetails: MutableSharedFlow<UserDetails> by lazy { MutableSharedFlow() }
    val userDetails = _userDetails.asSharedFlow()

    fun getUserDetails(userId:String) {
        viewModelScope.launch {
            _showProgressBar.emit(EmptyView.State.LOADING)

            when (val result = getUserInteractor(userId)) {
                is ActionResult.Success -> {
                    _userDetails.emit(result.data)
                }
            }
            _showProgressBar.emit(EmptyView.State.HIDE)

        }
    }


}