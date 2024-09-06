package com.missclick.spy.core.domain

import com.missclick.spy.core.data.DeviceRepo
import com.missclick.spy.core.data.WordRepo

class GetCollectionsUseCase(
    private val deviceRepo: DeviceRepo,
    private val wordRepo: WordRepo,
) {

    suspend operator fun invoke(): Result<List<String>> {
        return try {
            val languageCode = deviceRepo.getCurrentLanguageCode()
            val collections = wordRepo.getCollections(languageCode)
            Result.success(collections)
        } catch (e: Throwable) {
            Result.failure(e)
        }
    }
}

