package com.ml.truckingandconstructionwork

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ml.truckingandconstructionwork.core.ActionResult
import com.ml.truckingandconstructionwork.domain.interactor.splash.GetUserDetailWithSharedPrefInteractor
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch

class MainActivityViewModel(private val getUserDetailWithSharedPrefInteractor: GetUserDetailWithSharedPrefInteractor):ViewModel() {


    private val _userId: MutableSharedFlow<String> by lazy { MutableSharedFlow() }
    val userId = _userId.asSharedFlow()

    init {
        getUserDetailWithSharedPref()
    }

   private fun getUserDetailWithSharedPref() {
        viewModelScope.launch {
            val result = getUserDetailWithSharedPrefInteractor()
            _userId.emit(result.id)

        }
    }
}