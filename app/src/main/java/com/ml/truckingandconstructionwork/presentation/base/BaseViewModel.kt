package com.ml.truckingandconstructionwork.presentation.base

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import org.koin.core.component.KoinComponent

abstract class BaseViewModel:ViewModel(),KoinComponent {

    val _showNetworkError: MutableSharedFlow<() -> Unit> by lazy { MutableSharedFlow() }
    val showNetworkError = _showNetworkError.asSharedFlow()

    private val _showDefaultError = MutableSharedFlow<String?>()
    val showDefaultError = _showDefaultError.asSharedFlow()




    protected fun showDefaultError(errorMessage:String?) {
        viewModelScope.launch {
            _showDefaultError.emit(errorMessage)
        }
    }
}