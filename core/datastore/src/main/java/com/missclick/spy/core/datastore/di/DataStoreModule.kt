package com.missclick.spy.core.datastore.di

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.core.DataStoreFactory
import androidx.datastore.dataStoreFile
import com.missclick.spy.core.datastore.OptionsDataSource
import com.missclick.spy.core.datastore.preferences.OptionsDataSourceImpl
import com.missclick.spy.core.datastore.preferences.OptionsPreferences
import com.missclick.spy.core.datastore.preferences.OptionsPreferencesSerializer
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import org.koin.dsl.module

val dataStoreModule = module {
    single { OptionsPreferencesSerializer() }
    single { provideDataStore(get(), get()) }
    single<OptionsDataSource> { OptionsDataSourceImpl(get()) }
}

private fun provideDataStore(
    context: Context,
    optionsPreferencesSerializer: OptionsPreferencesSerializer,
): DataStore<OptionsPreferences> =
    DataStoreFactory.create(
        serializer = optionsPreferencesSerializer,
        scope = CoroutineScope(Dispatchers.IO),
    ) {
        context.dataStoreFile("options_preferences.pb")
    }