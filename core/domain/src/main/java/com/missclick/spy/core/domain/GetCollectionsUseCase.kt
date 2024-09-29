package com.missclick.spy.core.domain

import com.missclick.spy.core.data.DeviceRepo
import com.missclick.spy.core.data.WordRepo
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map

class GetCollectionsUseCase(
    private val deviceRepo: DeviceRepo,
    private val wordRepo: WordRepo,
) {

    operator fun invoke(): Flow<List<String>> {
//        return try {
//            val languageCode = deviceRepo.getCurrentLanguageCode()
//            val collections = wordRepo.getCollections(languageCode)
//            Result.success(collections)
//        } catch (e: Throwable) {
//            Result.failure(e)
//        }
        return flow {
            val languageCode = deviceRepo.getCurrentLanguageCode()
            wordRepo.getCollections(languageCode).collect {
                emit(it)
            }
        }
    }
}

