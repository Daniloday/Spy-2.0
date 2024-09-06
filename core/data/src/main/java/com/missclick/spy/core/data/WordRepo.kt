package com.missclick.spy.core.data

import com.missclick.spy.core.model.Word

interface WordRepo {

    suspend fun getWords(languageCode: String, collection: String): List<String>
    suspend fun getCollections(languageCode: String): List<String>
    suspend fun put(word: Word)
}