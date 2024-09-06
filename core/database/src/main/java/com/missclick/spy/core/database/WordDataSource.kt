package com.missclick.spy.core.database

import com.missclick.spy.core.database.room.SpyDatabase
import com.missclick.spy.core.database.room.WordDao
import com.missclick.spy.core.database.room.WordEntity
import com.missclick.spy.core.model.Word

interface WordDataSource {
    suspend fun getCollections(languageCode: String): List<String>
    suspend fun getWords(languageCode: String, collection: String): List<String>
    suspend fun insert(word: Word, languageCode: String)
}

