package com.missclick.spy.core.database.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.missclick.spy.core.database.enity.LanguageEntity
import com.missclick.spy.core.database.enity.LocationEntity
import com.missclick.spy.core.database.enity.SetEntity


@Database(
    entities = [
        LocationEntity::class,
        SetEntity::class,
        LanguageEntity::class,
    ],
    version = 1,
    autoMigrations = [

    ],
    exportSchema = true,
)
internal abstract class SpyDatabase : RoomDatabase() {
    abstract fun wordDao(): WordDao
}