package com.example.earsensei.sharedpreferences

import android.content.SharedPreferences

class LanguageSharedPreferences(private val sharedPreferences: SharedPreferences) :
    LanguagePreferences {
    override fun getLanguage(): String =
        sharedPreferences.getString(LANGUAGE, DEFAULT_VALUE) ?: DEFAULT_VALUE

    override fun saveLanguage(language: String) {
        sharedPreferences.edit().putString(LANGUAGE, language)
    }

    private companion object {
        const val LANGUAGE = "LANGUAGE"
        const val DEFAULT_VALUE = "PL"
    }
}