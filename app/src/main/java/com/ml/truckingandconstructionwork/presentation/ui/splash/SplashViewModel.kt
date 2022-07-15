package com.ml.truckingandconstructionwork.presentation.ui.splash

import androidx.lifecycle.viewModelScope
import com.ml.truckingandconstructionwork.domain.interactor.splash.GetUserDetailWithSharedPrefInteractor
import com.ml.truckingandconstructionwork.presentation.base.BaseViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch

class SplashViewModel(
    private val getUserDetailWithSharedPrefInteractor: GetUserDetailWithSharedPrefInteractor
) : BaseViewModel() {

    private val _userId: MutableSharedFlow<String> by lazy { MutableSharedFlow() }
    val userId = _userId.asSharedFlow()

    fun getUserDetailWithSharedPref() {
        viewModelScope.launch {
            val result = getUserDetailWithSharedPrefInteractor()
            _userId.emit(result.id)

        }
    }
}