package com.hardbobby.cryptocurrencyapp.presentation.main.viewmodel

import com.hardbobby.cryptocurrencyapp.presentation.base.BaseViewModel
import com.hardbobby.cryptocurrencyapp.presentation.utils.UserManager
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainHomeViewModel @Inject constructor(
    private val userManager: UserManager
) : BaseViewModel() {

    fun onUserSignOut() {
        userManager.endUserSession()
        postSnackbar("Berhasil Logout")
    }
}
