package com.missclick.spy.core.database.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.missclick.spy.core.model.Word


@Dao
internal interface WordDao {
    @Query("SELECT word_name FROM word WHERE language_code = :languageCode AND collection_name = :collection")
    fun getWords(
        languageCode: String,
        collection: String,
    ): List<String>

    @Query("SELECT DISTINCT collection_name FROM word WHERE language_code = :languageCode")
    fun getCollections(
        languageCode: String,
    ): List<String>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(word: WordEntity)
}