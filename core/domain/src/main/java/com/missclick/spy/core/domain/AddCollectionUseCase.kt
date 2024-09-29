package com.missclick.spy.core.domain

import com.missclick.spy.core.data.DeviceRepo
import com.missclick.spy.core.data.WordRepo
import com.missclick.spy.core.model.Collection

class AddCollectionUseCase(
    private val wordRepo: WordRepo,
    private val deviceRepo: DeviceRepo,
) {

    suspend operator fun invoke(name: String) {
        val currentLanguageCode = deviceRepo.getCurrentLanguageCode()
        val collection = Collection(
            name = name,
            isCustom = true
        )
        wordRepo.addCollection(
            collection = collection,
            languageCode = currentLanguageCode
        )
    }
}