package com.ml.truckingandconstructionwork.presentation.ui.settings

import androidx.lifecycle.viewModelScope
import com.ml.truckingandconstructionwork.domain.interactor.LogOutInteractor
import com.ml.truckingandconstructionwork.presentation.base.BaseViewModel
import com.ml.truckingandconstructionwork.presentation.custom_view.EmptyView
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch

class SettingsViewModel(private val logOutInteractor: LogOutInteractor) : BaseViewModel() {

    private val _showProgressBar: MutableSharedFlow<EmptyView.State> by lazy { MutableSharedFlow() }
    val showProgressBar = _showProgressBar.asSharedFlow()

    fun signOut() {
        viewModelScope.launch {
            _showProgressBar.emit(EmptyView.State.LOADING)
            logOutInteractor()


            _showProgressBar.emit(EmptyView.State.HIDE)
        }
    }
}