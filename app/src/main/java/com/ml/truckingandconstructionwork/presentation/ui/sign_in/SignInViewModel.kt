package com.ml.truckingandconstructionwork.presentation.ui.sign_in

import androidx.lifecycle.viewModelScope
import com.ml.truckingandconstructionwork.core.ActionResult
import com.ml.truckingandconstructionwork.domain.interactor.log_in.CheckUserDetailsInteractor
import com.ml.truckingandconstructionwork.domain.interactor.registration.GetUserDetailsInteractor
import com.ml.truckingandconstructionwork.domain.interactor.registration.SaveDataInSharedInteractor
import com.ml.truckingandconstructionwork.domain.models.registration.UserDetails
import com.ml.truckingandconstructionwork.presentation.base.BaseViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch

class SignInViewModel(
    private val checkUserDetailsInteractor: CheckUserDetailsInteractor,
    private val saveDataInSharedInteractor: SaveDataInSharedInteractor
    ) :
    BaseViewModel() {

    private val _showProgressBar: MutableSharedFlow<Boolean> by lazy { MutableSharedFlow() }
    val showProgressBar = _showProgressBar.asSharedFlow()

    private val _userId: MutableSharedFlow<String> by lazy { MutableSharedFlow() }
    val userId = _userId.asSharedFlow()

    fun checkUserDetail(email: String, password: String) {
        viewModelScope.launch {
            _showProgressBar.emit(true)


            when (val result =
                checkUserDetailsInteractor(UserDetails(email = email, password = password))) {
                is ActionResult.Success -> {

                    result.data?.let { _userId.emit(it) }

                }
            }

            _showProgressBar.emit(false)
        }
    }

    fun saveUserDetailsInSharedPref(userId:String) {
        viewModelScope.launch {
            saveDataInSharedInteractor(userId)
        }
    }
}