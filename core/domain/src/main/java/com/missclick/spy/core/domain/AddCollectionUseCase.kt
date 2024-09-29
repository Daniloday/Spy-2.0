package com.missclick.spy.core.domain

import com.missclick.spy.core.data.DeviceRepo
import com.missclick.spy.core.data.WordRepo
import com.missclick.spy.core.model.Set

class AddCollectionUseCase(
    private val wordRepo: WordRepo,
    private val deviceRepo: DeviceRepo,
) {

    suspend operator fun invoke(name: String) {
        val currentLanguageCode = deviceRepo.getCurrentLanguageCode()
        val set = Set(
            name = name,
            isCustom = true
        )
        wordRepo.addCollection(
            set = set,
            languageCode = currentLanguageCode
        )
    }
}