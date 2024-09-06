package com.missclick.spy.feature.collections

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class CollectionsViewModel(

) : ViewModel() {

    private val _viewState = MutableStateFlow<CollectionsViewState>(CollectionsViewState.Loading)
    val viewState = _viewState.asStateFlow()


}

sealed class CollectionsViewState() {
    data object Loading: CollectionsViewState()
    data class Success(val collections: List<String>): CollectionsViewState()
}