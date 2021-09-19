package com.hardbobby.cryptocurrencyapp.presentation.account.viewmodel

import com.hardbobby.cryptocurrencyapp.presentation.base.BaseViewModel
import com.hardbobby.cryptocurrencyapp.presentation.utils.UserManager
import javax.inject.Inject

class AccountViewModel @Inject constructor(
    private val userManager: UserManager
) : BaseViewModel() {

    fun onUserSignOut() {
        userManager.endUserSession()
        postSnackbar("Success logout")
    }
}