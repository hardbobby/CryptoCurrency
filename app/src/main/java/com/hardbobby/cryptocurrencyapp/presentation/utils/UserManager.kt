package com.hardbobby.cryptocurrencyapp.presentation.utils

import com.hardbobby.cryptocurrencyapp.presentation.utils.Constant.EMAIL
import com.hardbobby.cryptocurrencyapp.presentation.utils.Constant.IS_USER_LOGIN
import javax.inject.Inject

class UserManager @Inject constructor(private val preferencesManager: SharePreferencesManager) {
    var email = ""
        get() = if (field.isNotEmpty()) field else preferencesManager.getString(EMAIL)
        set(value) {
            field = value
            preferencesManager.save(EMAIL, value)
        }

    fun startUserSession() {
        preferencesManager.save(IS_USER_LOGIN, true)
    }

    fun isSessionActive(): Boolean {
        return preferencesManager.getBoolean(IS_USER_LOGIN)
    }

    fun endUserSession() {
        preferencesManager.save(IS_USER_LOGIN, false)
        preferencesManager.remove(EMAIL)
    }
}