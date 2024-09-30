package com.missclick.spy.core.database.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.missclick.spy.core.database.enity.LanguageEntity
import com.missclick.spy.core.database.enity.WordEntity
import com.missclick.spy.core.database.enity.SetEntity
import kotlinx.coroutines.flow.Flow


@Dao
internal interface WordDao {
    @Query("""
        SELECT word.name FROM word
        INNER JOIN `set` ON word.set_id = `set`.id
        INNER JOIN language ON `set`.language_id = language.id
        WHERE `set`.name = :collectionName AND code = :languageCode
    """)
    fun getWords(
        collectionName: String,
        languageCode: String
    ): Flow<List<String>>

    @Query("""
       SELECT DISTINCT `set`.name FROM `set`
        INNER JOIN language ON `set`.language_id = language.id
        WHERE code = :languageCode
    """)
    fun getCollections(
        languageCode: String,
    ): Flow<List<String>>

    @Query("""
       SELECT * FROM `set` 
        WHERE language_id IN (
           SELECT id FROM language WHERE code = :languageCode
       ) AND `set`.name = :collectionName
    """)
    fun getCollection(
        collectionName: String,
        languageCode: String,
    ): SetEntity

    @Query("""
       SELECT * FROM language WHERE code = :languageCode
    """)
    fun getLanguage(
        languageCode: String,
    ): LanguageEntity

    @Query("""
       SELECT * FROM language
    """)
    fun getLanguages(
    ): List<LanguageEntity>

    @Query("""
       SELECT DISTINCT `set`.name FROM `set`
        INNER JOIN language ON `set`.language_id = language.id
        WHERE code = :languageCode LIMIT 1
    """)
    fun getDefaultCollection(
        languageCode: String,
    ): String

    @Query("""
       SELECT DISTINCT code FROM language LIMIT 1
    """)
    fun getDefaultLanguage(): String

    @Query("""
       SELECT EXISTS(SELECT 1 FROM language WHERE code = :languageCode LIMIT 1)
    """)
    fun isExistLanguage(languageCode: String): Boolean

    @Query("""
       DELETE FROM word WHERE name =:wordName
    """)
    fun deleteWord(wordName: String)

    @Query("""
       DELETE FROM `set`
       WHERE language_id IN (
           SELECT id FROM language WHERE code = :languageCode
       ) AND `set`.name = :collectionName
    """)
    fun deleteCollection(collectionName: String, languageCode: String)

    @Query("""
       SELECT DISTINCT code FROM language
        INNER JOIN `set` ON `set`.language_id = language.id
        WHERE `set`.name = :collectionName LIMIT 1
    """)
    fun getCollectionLanguage(collectionName: String): String

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertWord(word: WordEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertCollection(collection: SetEntity)
}