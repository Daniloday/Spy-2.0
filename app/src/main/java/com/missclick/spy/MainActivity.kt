package com.missclick.spy

import android.app.Activity
import android.app.LocaleManager
import android.content.Context
import android.content.Intent
import android.content.res.Configuration
import android.content.res.Resources
import android.os.Build
import android.os.Bundle
import android.os.LocaleList
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatDelegate
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.res.stringResource
import androidx.core.os.LocaleListCompat
import androidx.lifecycle.lifecycleScope
import com.google.android.gms.ads.MobileAds
import com.missclick.spy.core.advertising.BottomAds
import com.missclick.spy.core.advertising.InterstitialAdManager
import com.missclick.spy.core.advertising.InterstitialAdManagerImpl
import com.missclick.spy.core.data.OptionsRepo
import com.missclick.spy.core.database.WordDataSource
import com.missclick.spy.core.domain.GetOptionsUseCase
import com.missclick.spy.core.navigation.NavGraph
import com.missclick.spy.core.ui.theme.AppTheme
import com.missclick.spy.core.ui.theme.SpyTheme
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import org.koin.android.ext.android.inject
import java.util.Locale

class MainActivity : ComponentActivity() {

    private val getOptionsUseCase by inject<GetOptionsUseCase>()
    private val dev by inject<WordDataSource>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        lifecycleScope.launch(Dispatchers.IO) {
            dev.addWordsForDev()
            getOptionsUseCase().collect { options ->
                setLanguage(options.languageCode)
            }
        }
        setContent {
            SpyTheme {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(
                            brush = AppTheme.colors.background
                        )
                ) {
                    NavGraph(modifier = Modifier.weight(1f))
                    BottomAds()
                }
            }
        }
    }

    private fun setLanguage(languageCode: String) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            getSystemService(LocaleManager::class.java)
                .applicationLocales = LocaleList.forLanguageTags(languageCode)
        } else {
            val locale = Locale(languageCode)
            Locale.setDefault(locale)
            val configuration = resources.configuration
            configuration.setLocale(locale)
            resources.updateConfiguration(configuration, resources.displayMetrics)
        }
    }
}

