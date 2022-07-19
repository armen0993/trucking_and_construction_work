package com.ml.truckingandconstructionwork.presentation.ui.offers

import androidx.lifecycle.viewModelScope
import com.ml.truckingandconstructionwork.core.ActionResult
import com.ml.truckingandconstructionwork.domain.interactor.offer.GetOffersInteractor
import com.ml.truckingandconstructionwork.domain.models.add_work.Offer
import com.ml.truckingandconstructionwork.presentation.base.BaseViewModel
import com.ml.truckingandconstructionwork.presentation.custom_view.EmptyView
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch

class OffersListViewModel(
    private val getOffersInteractor: GetOffersInteractor
) : BaseViewModel() {

    private val _showProgressBar: MutableSharedFlow<EmptyView.State> by lazy { MutableSharedFlow() }
    val showProgressBar = _showProgressBar.asSharedFlow()

    private val _listOffers: MutableSharedFlow<List<Offer>> by lazy { MutableSharedFlow() }
    val listOffers = _listOffers.asSharedFlow()

    private var list = listOf<Offer>()


    fun getOffersList() {
        viewModelScope.launch {
            _showProgressBar.emit(EmptyView.State.LOADING)


            when (val result = getOffersInteractor()) {
                is ActionResult.Success -> {
                    list = result.data
                    _listOffers.emit(list)
                }
            }


            if (list.isEmpty()) {
                _showProgressBar.emit(EmptyView.State.EMPTY)
            } else {
            _showProgressBar.emit(EmptyView.State.HIDE)
              }

        }
    }

}
