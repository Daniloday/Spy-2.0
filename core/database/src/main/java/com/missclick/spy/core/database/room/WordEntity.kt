package com.missclick.spy.core.database.room

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.missclick.spy.core.model.Word

@Entity(tableName = "word")
internal data class WordEntity(
    @PrimaryKey(autoGenerate = true) val id: Int? = null,
    @ColumnInfo(name = "word_name") val wordName: String,
    @ColumnInfo(name = "collection_name") val collectionName: String,
    @ColumnInfo(name = "language_code") val languageCode: String,
    @ColumnInfo(name = "is_hidden") val isHidden: Boolean = false,
)

internal fun WordEntity.asModel() = Word(
    wordName = wordName,
    collectionName = collectionName,
    isHidden = isHidden
)

internal fun Word.asEntity(languageCode: String) = WordEntity(
    wordName = wordName,
    collectionName = collectionName,
    isHidden = isHidden,
    languageCode = languageCode
)

