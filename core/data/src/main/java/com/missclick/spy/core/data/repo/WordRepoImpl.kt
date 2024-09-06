package com.missclick.spy.core.data.repo

import com.missclick.spy.core.data.WordRepo
import com.missclick.spy.core.database.WordDataSource
import com.missclick.spy.core.model.Word

internal class WordRepoImpl(
    private val wordDataSource: WordDataSource
) : WordRepo {

    override suspend fun getWords(languageCode: String, collection: String): List<String> {
        return wordDataSource.getWords(languageCode = languageCode, collection = collection)
    }

    override suspend fun getCollections(languageCode: String): List<String> {
        return wordDataSource.getCollections(languageCode)
    }

    override suspend fun put(word: Word) {
        wordDataSource.insert(word, "en")
    }
}