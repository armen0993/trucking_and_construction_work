package com.ml.truckingandconstructionwork.presentation.ui.add_work.add_offer

import androidx.lifecycle.viewModelScope
import com.ml.truckingandconstructionwork.core.ActionResult
import com.ml.truckingandconstructionwork.domain.interactor.add_work.SetOfferInteractor
import com.ml.truckingandconstructionwork.domain.models.add_work.Offer
import com.ml.truckingandconstructionwork.presentation.base.BaseViewModel
import com.ml.truckingandconstructionwork.presentation.custom_view.EmptyView
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch

class AddJobOfferViewModel(
    private val setOfferInteractor: SetOfferInteractor
):BaseViewModel() {

    private val _showProgressBar: MutableSharedFlow<EmptyView.State> by lazy { MutableSharedFlow() }
    val showProgressBar = _showProgressBar.asSharedFlow()

    private val _success: MutableSharedFlow<Boolean> by lazy { MutableSharedFlow() }
    val success = _success.asSharedFlow()

    fun setOffer(offerModel: Offer){
        viewModelScope.launch {
            _showProgressBar.emit(EmptyView.State.LOADING)
            when (val result = setOfferInteractor(offerModel)) {
                is ActionResult.Success -> {
                    delay(2000)
                    _success.emit(result.data)
                }
            }
            _showProgressBar.emit(EmptyView.State.HIDE)
        }
    }
}