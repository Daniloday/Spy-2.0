package com.missclick.spy.feature.words

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.missclick.spy.core.data.OptionsRepo
import com.missclick.spy.core.data.WordRepo
import com.missclick.spy.core.domain.GetOptionsUseCase
import com.missclick.spy.core.domain.SetCollectionUseCase
import com.missclick.spy.core.model.Collection
import com.missclick.spy.core.model.Word
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class WordsViewModel(
    private val setCollectionUseCase: SetCollectionUseCase,
    private val getOptionsUseCase: GetOptionsUseCase,
    private val wordsRepo: WordRepo,
) : ViewModel() {


    private val _viewState = MutableStateFlow<WordsViewState>(WordsViewState.Loading)
    val viewState = _viewState.asStateFlow()

    fun loadData(selectedCollectionName: String) {
        viewModelScope.launch(Dispatchers.IO) {
            val options = getOptionsUseCase().first()
            val collection = wordsRepo.getCollection(selectedCollectionName, options.languageCode)
            val getWordsResult = wordsRepo.getWords(collection.name, options.languageCode)
            getWordsResult.collect {
                initSuccess(collection, it)
            }
        }
    }

    private fun initSuccess(selectedCollection: Collection, words: List<String>) {
        val successState = viewState.value as? WordsViewState.Success
        _viewState.update {
            WordsViewState.Success(
                words = words,
                isEnteringNewWord = successState?.isEnteringNewWord ?: false,
                newWord = successState?.newWord ?: "",
                collectionName = selectedCollection.name,
                isEditable = selectedCollection.isCustom
            )
        }
    }

    suspend fun saveCollection() {
        withContext(Dispatchers.IO) {
            val successState = viewState.value as? WordsViewState.Success ?: return@withContext
            setCollectionUseCase(successState.collectionName)
        }
    }

    fun deleteCollection() {
        val successState = viewState.value as? WordsViewState.Success ?: return
        viewModelScope.launch(Dispatchers.IO) {
            val options = getOptionsUseCase().first()
            wordsRepo.deleteCollection(successState.collectionName, options.languageCode)
        }
    }

    fun deleteWord(name: String) {
        viewModelScope.launch(Dispatchers.IO) {
            wordsRepo.deleteWord(name)
        }
    }

    fun addWord() {
        val successState = viewState.value as? WordsViewState.Success ?: return
        _viewState.update {
            successState.copy(
                isEnteringNewWord = true,
                newWord = ""
            )
        }
    }

    fun saveNewWord() {
        val successState = viewState.value as? WordsViewState.Success ?: return
        if (successState.newWord.isNotBlank()) {
            val word = Word(
                wordName = successState.newWord,
                isHidden = false
            )
            viewModelScope.launch(Dispatchers.IO) {
                val options = getOptionsUseCase().first()
                wordsRepo.addWord(word, successState.collectionName, options.languageCode)
            }
        }
        _viewState.update {
            successState.copy(
                isEnteringNewWord = false,
                newWord = ""
            )
        }
    }

    fun onNewWordChange(newWord: String) {
        val successState = viewState.value as? WordsViewState.Success ?: return
        _viewState.update {
            successState.copy(
                newWord = newWord
            )
        }
    }

}

sealed class WordsViewState {
    data object Loading: WordsViewState()
    data class Success(
        val collectionName: String,
        val isEnteringNewWord: Boolean = false,
        val newWord: String = "",
        val isEditable: Boolean,
        val words: List<String>,
    ): WordsViewState()
}