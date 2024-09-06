package com.missclick.spy.core.domain

import com.missclick.spy.core.data.DeviceRepo
import com.missclick.spy.core.data.OptionsRepo
import com.missclick.spy.core.data.WordRepo
import kotlinx.coroutines.flow.first
import kotlin.random.Random

class GetRandomWordUseCase(
    private val deviceRepo: DeviceRepo,
    private val wordRepo: WordRepo,
    private val optionsRepo: OptionsRepo,
) {

    suspend operator fun invoke(): Result<String> {
        return try {
            val collection = "col1"//optionsRepo.options.first().collectionName
            val languageCode = deviceRepo.getCurrentLanguageCode()
            val words = wordRepo.getWords(
                languageCode = languageCode,
                collection = collection
            )
            val randomWord = words.random(Random(System.currentTimeMillis()))
            Result.success(randomWord)
        } catch (e: Throwable) {
            Result.failure(e)
        }
    }
}