package com.missclick.spy.feature.collections

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.missclick.spy.core.data.WordRepo
import com.missclick.spy.core.domain.AddCollectionUseCase
import com.missclick.spy.core.domain.GetOptionsUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class CollectionsViewModel(
    private val wordRepo: WordRepo,
    private val getOptionsUseCase: GetOptionsUseCase,
    private val addCollectionUseCase: AddCollectionUseCase,
) : ViewModel() {

    private val _viewState = MutableStateFlow<CollectionsViewState>(CollectionsViewState.Loading)
    val viewState = _viewState.asStateFlow()

    init {
        viewModelScope.launch(Dispatchers.IO) {
            val options = getOptionsUseCase().first()
            val selectedCollection = options.collectionName
            wordRepo.getCollections(options.languageCode).collect { collections ->
                initSuccess(
                    selectedCollection = selectedCollection,
                    collectionNames = collections
                )
            }
        }
    }


    private fun initSuccess(
        collectionNames: List<String>,
        selectedCollection: String,
    ) {
        val collectionViews = collectionNames.map { collectionName ->
            CollectionView(
                name = collectionName,
                isSelected = collectionName == selectedCollection
            )
        }
        val successState = viewState.value as? CollectionsViewState.Success
        _viewState.update {
            CollectionsViewState.Success(
                collectionViews = collectionViews,
                isEnteringNewCollection = successState?.isEnteringNewCollection ?: false,
                newCollection = successState?.newCollection ?: "",
            )
        }
    }

    fun addNewCollection() {
        val successState = viewState.value as? CollectionsViewState.Success ?: return
        _viewState.update {
            successState.copy(
                isEnteringNewCollection = true,
                newCollection = ""
            )
        }
    }

    fun saveNewCollection() {
        val successState = viewState.value as? CollectionsViewState.Success ?: return
        if (successState.newCollection.isNotBlank()) {
            viewModelScope.launch(Dispatchers.IO) {
                addCollectionUseCase.invoke(successState.newCollection)
            }
        }
        _viewState.update {
            successState.copy(
                isEnteringNewCollection = false,
                newCollection = ""
            )
        }
    }

    fun onNewCollectionNameChange(newName: String) {
        val successState = viewState.value as? CollectionsViewState.Success ?: return
        _viewState.update {
            successState.copy(
                newCollection = newName
            )
        }
    }

}

sealed class CollectionsViewState {
    data object Loading: CollectionsViewState()
    data class Success(
        val collectionViews: List<CollectionView>,
        val isEnteringNewCollection: Boolean,
        val newCollection: String,
    ): CollectionsViewState()
}

data class CollectionView(
    val name: String,
    val isSelected: Boolean
)