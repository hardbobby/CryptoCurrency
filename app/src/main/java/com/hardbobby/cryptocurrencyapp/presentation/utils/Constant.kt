package com.hardbobby.cryptocurrencyapp.presentation.utils

object Constant {

    const val SHARED_PREFERENCE_NAME = "UserPref_Crypto"
    const val IS_USER_LOGIN = "IS_USER_LOGIN"
    const val EMAIL = "email"

}

sealed class LoginFlow {
    object FormLogin : LoginFlow()
    object MainHomePage : LoginFlow()
}