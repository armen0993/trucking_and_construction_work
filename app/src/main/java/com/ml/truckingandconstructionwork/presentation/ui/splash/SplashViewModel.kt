package com.ml.truckingandconstructionwork.presentation.ui.splash

import androidx.lifecycle.viewModelScope
import com.ml.truckingandconstructionwork.domain.interactor.splash.GetSkippedTypeWithSharedPrefInteractor
import com.ml.truckingandconstructionwork.domain.interactor.splash.GetUserDetailWithSharedPrefInteractor
import com.ml.truckingandconstructionwork.presentation.base.BaseViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch

class SplashViewModel(
    private val getUserDetailWithSharedPrefInteractor: GetUserDetailWithSharedPrefInteractor,
    private val getSkippedTypeWithSharedPrefInteractor: GetSkippedTypeWithSharedPrefInteractor
) : BaseViewModel() {

    private val _userId: MutableSharedFlow<String> by lazy { MutableSharedFlow() }
    val userId = _userId.asSharedFlow()

    private val _skipped: MutableSharedFlow<Boolean> by lazy { MutableSharedFlow() }
    val skipped = _skipped.asSharedFlow()

    fun getUserDetailWithSharedPref() {
        viewModelScope.launch {
            val result = getUserDetailWithSharedPrefInteractor()
            _userId.emit(result.id)

        }
    }

    fun getSkippedTypeWithSharedPref() {
        viewModelScope.launch {
            val result = getSkippedTypeWithSharedPrefInteractor()
            _skipped.emit(result)

        }
    }
}