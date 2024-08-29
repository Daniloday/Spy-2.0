package com.missclick.spy

import android.app.Application
import android.content.res.Configuration
import android.content.res.Resources
import java.util.Locale

class SpyApp(): Application() {

    override fun onCreate() {
        super.onCreate()
        val locale = Locale("en")
        Locale.setDefault(locale)

        val config = Configuration(resources.configuration)
        config.setLocale(locale)
        Resources.getSystem().updateConfiguration(config, null)
        createConfigurationContext(config)
        println(Resources.getSystem().configuration.locales[0].language)
    }
}