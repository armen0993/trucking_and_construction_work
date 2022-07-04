package com.ml.truckingandconstructionwork.presentation.ui.job_offer.offers_list

import androidx.lifecycle.viewModelScope
import com.ml.truckingandconstructionwork.domain.models.OffersListModel
import com.ml.truckingandconstructionwork.presentation.base.BaseViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch

class OffersListViewModel:BaseViewModel() {

    private val _showProgressBar: MutableSharedFlow<Boolean> by lazy { MutableSharedFlow() }
    val showProgressBar = _showProgressBar.asSharedFlow()

    private val _listOffers: MutableSharedFlow<List<OffersListModel>> by lazy { MutableSharedFlow() }
    val listOffers = _listOffers.asSharedFlow()
var list = listOf<OffersListModel>()

    fun setUserDetails() {
        viewModelScope.launch {
          list = listOf(
                OffersListModel(12,"truk","yeesss")
            )
            _showProgressBar.emit(true)

            _listOffers.emit(list)
            _showProgressBar.emit(false)
        }
    }
}
