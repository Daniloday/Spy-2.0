package com.missclick.spy.core.database.room

import androidx.room.Database
import androidx.room.RoomDatabase


@Database(entities = [WordEntity::class], version = 1)
internal abstract class SpyDatabase : RoomDatabase() {
    abstract fun wordDao(): WordDao
}