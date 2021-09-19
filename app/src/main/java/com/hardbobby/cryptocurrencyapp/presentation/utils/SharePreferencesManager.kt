package com.hardbobby.cryptocurrencyapp.presentation.utils

import android.content.SharedPreferences
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SharePreferencesManager @Inject constructor(private val sharedPreferences: SharedPreferences) {
    private val editor = sharedPreferences.edit()

    fun save(key: String, value: Boolean) {
        editor.putBoolean(key, value)
        editor.apply()
    }

    fun save(key: String, value: String) {
        editor.putString(key, value)
        editor.apply()
    }

    fun getBoolean(key: String): Boolean = sharedPreferences.getBoolean(key, false)

    fun getString(key: String): String = sharedPreferences.getString(key, "").orEmpty()

    fun remove(key: String) {
        editor.remove(key)
        editor.apply()
    }
}