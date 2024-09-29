package com.missclick.spy.core.database.di

import android.content.Context
import androidx.room.Room
import com.missclick.spy.core.database.WordDataSource
import com.missclick.spy.core.database.WordDataSourceImpl
import com.missclick.spy.core.database.room.SpyDatabase
import org.koin.dsl.module

val databaseModule = module {
    single { provideDatabase(get()) }
    single<WordDataSource> { WordDataSourceImpl(get()) }
}

private fun provideDatabase(applicationContext: Context): SpyDatabase {
    return Room.databaseBuilder(
        applicationContext,
        SpyDatabase::class.java,
        "spy-database"
    )
        .createFromAsset("spy-database-preload.db")
        .build()
}