package com.missclick.spy

import android.app.Application
import android.content.Context
import android.content.res.Configuration
import android.content.res.Resources
import androidx.appcompat.app.AppCompatDelegate
import androidx.core.os.LocaleListCompat
import com.google.android.gms.ads.MobileAds
import com.missclick.spy.core.advertising.di.advertisingModule
import com.missclick.spy.core.data.di.dataModule
import com.missclick.spy.core.database.di.databaseModule
import com.missclick.spy.core.datastore.di.dataStoreModule
import com.missclick.spy.core.device.di.deviceModule
import com.missclick.spy.core.domain.di.domainModule
import com.missclick.spy.feature.collections.di.collectionsModule
import com.missclick.spy.feature.game.di.gameModule
import com.missclick.spy.feature.game_options.di.gameOptionsModule
import com.missclick.spy.feature.settings.di.settingsModule
import com.missclick.spy.feature.words.di.wordsModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.dsl.module
import java.util.Locale

class SpyApp() : Application() {

    override fun onCreate() {
        super.onCreate()
        MobileAds.initialize(this)
        val m = listOf(
            dataModule,
            dataStoreModule,
            databaseModule,
            gameOptionsModule,
            deviceModule,
            domainModule,
            gameModule,
            collectionsModule,
            wordsModule,
            settingsModule,
            advertisingModule
        )

        startKoin {
            androidLogger()
            androidContext(this@SpyApp)
            modules(m)
        }
    }

}