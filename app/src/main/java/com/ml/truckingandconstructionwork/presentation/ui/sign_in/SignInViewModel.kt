package com.ml.truckingandconstructionwork.presentation.ui.sign_in

import androidx.lifecycle.viewModelScope
import com.ml.truckingandconstructionwork.core.ActionResult
import com.ml.truckingandconstructionwork.domain.interactor.log_in.CheckUserDetailsInteractor
import com.ml.truckingandconstructionwork.domain.interactor.log_in.SetSkippedTypeInSharedInteractor
import com.ml.truckingandconstructionwork.domain.interactor.registration.GetUserDetailsInteractor
import com.ml.truckingandconstructionwork.domain.interactor.registration.SaveDataInSharedInteractor
import com.ml.truckingandconstructionwork.domain.models.registration.UserDetails
import com.ml.truckingandconstructionwork.presentation.base.BaseViewModel
import com.ml.truckingandconstructionwork.presentation.custom_view.EmptyView
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch

class SignInViewModel(
    private val checkUserDetailsInteractor: CheckUserDetailsInteractor,
    private val saveDataInSharedInteractor: SaveDataInSharedInteractor,
    private val setSkippedTypeInSharedInteractor: SetSkippedTypeInSharedInteractor
) :
    BaseViewModel() {

    private val _showProgressBar: MutableSharedFlow<EmptyView.State> by lazy { MutableSharedFlow() }
    val showProgressBar = _showProgressBar.asSharedFlow()

    private val _userId: MutableSharedFlow<String> by lazy { MutableSharedFlow() }
    val userId = _userId.asSharedFlow()

    private val _signed: MutableSharedFlow<Pair<Boolean, String>> by lazy { MutableSharedFlow() }
    val signed = _signed.asSharedFlow()

    fun checkUserDetail(email: String, password: String) {
        viewModelScope.launch {
            _showProgressBar.emit(EmptyView.State.LOADING)
            when (val result =
                checkUserDetailsInteractor(UserDetails(email = email, password = password))) {
                is ActionResult.Success -> {
                    result.data?.let { _userId.emit(it) }
                }
            }
            _showProgressBar.emit(EmptyView.State.HIDE)
        }
    }

    fun saveUserDetailsInSharedPref(userId: String) {
        viewModelScope.launch {
            saveDataInSharedInteractor(userId)
        }
    }

    fun saveSkippedTypeInSharedPref(boolean: Boolean) {
        viewModelScope.launch {
            setSkippedTypeInSharedInteractor(boolean)
        }
    }

    fun signedIn(signed: Boolean, userId: String) {
        viewModelScope.launch {
            _signed.emit(Pair(signed, userId))
        }
    }
}