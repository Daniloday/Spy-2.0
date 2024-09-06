package com.missclick.spy

import android.app.Application
import android.content.Context
import android.content.res.Configuration
import android.content.res.Resources
import com.missclick.spy.core.data.di.dataModule
import com.missclick.spy.core.database.di.databaseModule
import com.missclick.spy.core.datastore.di.dataStoreModule
import com.missclick.spy.core.device.di.deviceModule
import com.missclick.spy.core.domain.di.domainModule
import com.missclick.spy.feature.collections.di.collectionsModule
import com.missclick.spy.feature.game.di.gameModule
import com.missclick.spy.feature.game_options.di.gameOptionsModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.dsl.module
import java.util.Locale

class SpyApp() : Application() {

    override fun onCreate() {
        super.onCreate()
        val m = listOf(
            dataModule,
            dataStoreModule,
            databaseModule,
            gameOptionsModule,
            deviceModule,
            domainModule,
            gameModule,
            collectionsModule,
        )

        startKoin {
            androidLogger()
            androidContext(this@SpyApp)
            modules(m)
        }
    }

}