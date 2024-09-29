package com.missclick.spy.core.domain

import com.missclick.spy.core.data.DeviceRepo
import com.missclick.spy.core.data.OptionsRepo

class SetCollectionUseCase(
    private val optionsRepo: OptionsRepo,
    private val deviceRepo: DeviceRepo,
) {

    suspend operator fun invoke(collectionName: String) {
        val currentLanguageCode = deviceRepo.getCurrentLanguageCode()
        optionsRepo.setCollectionName(
            collectionName = collectionName,
            languageCode = currentLanguageCode,
        )
    }

}