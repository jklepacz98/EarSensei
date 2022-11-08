package com.example.earsensei.sharedpreferences

interface LanguagePreferences {
    fun getLanguage(): String
    fun saveLanguage(language: String)
}