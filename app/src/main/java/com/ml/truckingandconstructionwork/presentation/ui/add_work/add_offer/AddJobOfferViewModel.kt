package com.ml.truckingandconstructionwork.presentation.ui.add_work.add_offer

import androidx.lifecycle.viewModelScope
import com.ml.truckingandconstructionwork.domain.interactor.add_work.SetOfferInteractor
import com.ml.truckingandconstructionwork.domain.models.add_work.Offer
import com.ml.truckingandconstructionwork.presentation.base.BaseViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch

class AddJobOfferViewModel(
    private val setOfferInteractor: SetOfferInteractor
):BaseViewModel() {

    private val _showProgressBar: MutableSharedFlow<Boolean> by lazy { MutableSharedFlow() }
    val showProgressBar = _showProgressBar.asSharedFlow()

    fun setOffer(offerModel: Offer){
        viewModelScope.launch {
            _showProgressBar.emit(true)
           setOfferInteractor(offerModel)
            _showProgressBar.emit(false)
        }
    }
}