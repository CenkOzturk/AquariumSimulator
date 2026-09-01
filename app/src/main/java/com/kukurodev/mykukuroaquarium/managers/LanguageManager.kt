package com.kukurodev.mykukuroaquarium.managers

import android.app.Activity
import android.content.Context
import android.content.res.Configuration
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import com.kukurodev.mykukuroaquarium.model.AppLanguage
import java.util.Locale

object LanguageManager {
    private const val PREF_NAME = "settings"
    private const val LANGUAGE_KEY = "language"

    var currentLanguage by mutableStateOf(AppLanguage.ENGLISH)
        private set

    fun init(context: Context) {
        val savedLanguage = context
            .getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)
            .getString(
                LANGUAGE_KEY,
                AppLanguage.ENGLISH.name
            )

        currentLanguage = runCatching {
            AppLanguage.valueOf(savedLanguage!!)
        }.getOrDefault(AppLanguage.ENGLISH)

        applyLocale(context, currentLanguage)
    }

    fun setLanguage(
        context: Context,
        language: AppLanguage
    ) {
        currentLanguage = language

        context
            .getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)
            .edit()
            .putString(LANGUAGE_KEY, language.name)
            .apply()

        applyLocale(context, language)

        (context as Activity).recreate()
    }

    private fun applyLocale(
        context: Context,
        language: AppLanguage
    ) {
        val locale = when (language) {
            AppLanguage.ENGLISH -> Locale("en")
            AppLanguage.TURKISH -> Locale("tr")
        }

        Locale.setDefault(locale)

        val configuration = Configuration(
            context.resources.configuration
        )

        configuration.setLocale(locale)

        context.resources.updateConfiguration(
            configuration,
            context.resources.displayMetrics
        )
    }
}