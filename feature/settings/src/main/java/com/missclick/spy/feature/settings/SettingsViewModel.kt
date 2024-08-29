package com.missclick.spy.feature.settings

import android.app.Application
import android.content.Context
import android.content.Intent
import android.content.res.Configuration
import android.content.res.Resources
import androidx.core.content.ContextCompat.startActivity
import androidx.lifecycle.AndroidViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import java.util.Locale

class SettingsViewModel(private val application: Application): AndroidViewModel(application ) {

    private val _viewState = MutableStateFlow<SettingsViewState>(SettingsViewState())
    val viewState = _viewState.asStateFlow()

    init {

        val lang = Resources.getSystem().configuration.locales[0].language
        _viewState.update { state ->
            state.copy(
                languages = state.languages.map {
                    if (it.isoCode == lang) {
                        return@map it.copy(isSelected = true)
                    }
                    it.copy(isSelected = false)
                }
            )
        }
    }

    fun selectLanguage(
        selectedLanguage: Language,
    ) {

        val locale = Locale("ru")
        Locale.setDefault(locale)

        val config = Configuration(application.resources.configuration)
        config.setLocale(locale)

        application.createConfigurationContext(config)

//        val intent = Intent(this, MainActivity::class.java)
//        application.startActivity(intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK))

        _viewState.update { state ->
            state.copy(
                languages = state.languages.map {
                    if (it.name == selectedLanguage.name) {
                        return@map it.copy(isSelected = true)
                    }
                    it.copy(isSelected = false)
                }
            )
        }
    }

}

data class SettingsViewState(
    val languages: List<Language> = listOf(
        Language("Rus", "ru"),
        Language("English", "en"),
        Language("Ukr", "uk"),
    ),
)

data class Language(
    val name: String,
    val isoCode: String,
    val isSelected: Boolean = false,
)