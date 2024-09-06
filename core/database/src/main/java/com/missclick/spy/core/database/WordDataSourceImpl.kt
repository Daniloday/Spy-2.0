package com.missclick.spy.core.database

import com.missclick.spy.core.database.room.SpyDatabase
import com.missclick.spy.core.database.room.WordEntity
import com.missclick.spy.core.database.room.asEntity
import com.missclick.spy.core.database.room.asModel
import com.missclick.spy.core.model.Word

internal class WordDataSourceImpl(
    private val db: SpyDatabase
): WordDataSource {

    override suspend fun getCollections(languageCode: String): List<String> {
        return db.wordDao().getCollections(languageCode)
    }

    override suspend fun getWords(languageCode: String, collection: String): List<String> {
        return db.wordDao().getWords(languageCode = languageCode, collection = collection)
    }

    override suspend fun insert(word: Word, languageCode: String) {
        db.wordDao().insert(word.asEntity(languageCode))
    }
}