package com.missclick.spy.core.domain

import com.missclick.spy.core.common.Constant.MIN_LOCATIONS_TO_PLAY
import com.missclick.spy.core.data.DeviceRepo
import com.missclick.spy.core.data.OptionsRepo
import com.missclick.spy.core.data.WordRepo
import com.missclick.spy.core.model.Options
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.CoroutineStart
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.runBlocking

class GetOptionsUseCase(
    private val optionsRepo: OptionsRepo,
    private val deviceRepo: DeviceRepo,
    private val wordsRepo: WordRepo,
) {

    operator fun invoke(): Flow<Options> = optionsRepo.options.map { optionsRaw ->
        val options = if (optionsRaw.languageCode.isNotEmpty()) {
            optionsRaw
        } else {
            val currentLanguage = deviceRepo.getCurrentLanguageCode()
            val isExistCurrentLanguage = wordsRepo.checkIsExistLanguage(currentLanguage)
            val newLanguageCode = if (isExistCurrentLanguage) {
                currentLanguage
            } else {
                wordsRepo.getDefaultLanguage()
            }
            optionsRaw.copy(
                languageCode = newLanguageCode
            )
        }

        val selectCollectionLanguageCode = wordsRepo.getCollectionLanguage(options.collectionName)
        val words = wordsRepo.getWords(options.collectionName, options.languageCode).first()

        if (
            selectCollectionLanguageCode != options.languageCode
            || words.size < MIN_LOCATIONS_TO_PLAY
        ) {
            val defaultCollection = wordsRepo.getDefaultCollection(options.languageCode)
            return@map options.copy(collectionName = defaultCollection)
        }

        options
    }.flowOn(Dispatchers.IO)

}
