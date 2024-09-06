package com.missclick.spy.core.domain

import com.missclick.spy.core.data.DeviceRepo
import com.missclick.spy.core.data.OptionsRepo
import com.missclick.spy.core.data.WordRepo
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.first
import kotlin.random.Random

class GetWordsUseCase(
    private val deviceRepo: DeviceRepo,
    private val wordRepo: WordRepo,
    private val optionsRepo: OptionsRepo,
) {

    suspend operator fun invoke(): Result<List<String>> {
        return try {
            val collection = optionsRepo.options.first().collectionName
            val languageCode = deviceRepo.getCurrentLanguageCode()
            val words = wordRepo.getWords(
                languageCode = languageCode,
                collection = collection
            )
            Result.success(words)
        } catch (e: Throwable) {
            Result.failure(e)
        }
    }
}




