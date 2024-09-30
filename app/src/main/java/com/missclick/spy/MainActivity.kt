package com.missclick.spy

import android.app.LocaleManager
import android.os.Build
import android.os.Bundle
import android.os.LocaleList
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.ui.Modifier
import androidx.lifecycle.lifecycleScope
import com.missclick.spy.core.advertising.BottomAds
import com.missclick.spy.core.domain.GetOptionsUseCase
import com.missclick.spy.core.navigation.NavGraph
import com.missclick.spy.core.ui.theme.AppTheme
import com.missclick.spy.core.ui.theme.SpyTheme
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.koin.android.ext.android.inject
import java.util.Locale

class MainActivity : ComponentActivity() {

    private val getOptionsUseCase by inject<GetOptionsUseCase>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        lifecycleScope.launch(Dispatchers.IO) {
            getOptionsUseCase().collect { options ->
                setLanguage(options.selectedLanguageCode)
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

