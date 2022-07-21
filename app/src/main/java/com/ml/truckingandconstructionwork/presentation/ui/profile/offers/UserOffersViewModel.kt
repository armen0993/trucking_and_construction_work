package com.ml.truckingandconstructionwork.presentation.ui.profile.offers

import androidx.lifecycle.viewModelScope
import com.ml.truckingandconstructionwork.core.ActionResult
import com.ml.truckingandconstructionwork.domain.interactor.offer.GetUserOffersInteractor
import com.ml.truckingandconstructionwork.domain.models.add_work.Offer
import com.ml.truckingandconstructionwork.presentation.base.BaseViewModel
import com.ml.truckingandconstructionwork.presentation.custom_view.EmptyView
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch

class UserOffersViewModel(private val getUserOffersInteractor: GetUserOffersInteractor):BaseViewModel() {
    private val _showProgressBar: MutableSharedFlow<EmptyView.State> by lazy { MutableSharedFlow() }
    val showProgressBar = _showProgressBar.asSharedFlow()

    private val _listOffer: MutableSharedFlow<List<Offer>> by lazy { MutableSharedFlow() }
    val listOffer = _listOffer.asSharedFlow()

    private var list = listOf<Offer>()

    fun getOffersList(userId:String) {
        viewModelScope.launch {
            _showProgressBar.emit(EmptyView.State.LOADING)


            when (val result = getUserOffersInteractor(userId)) {
                is ActionResult.Success -> {
                    list = result.data
                    _listOffer.emit(list)
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