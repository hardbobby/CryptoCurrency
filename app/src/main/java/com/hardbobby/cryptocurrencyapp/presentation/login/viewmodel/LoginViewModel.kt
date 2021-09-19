package com.hardbobby.cryptocurrencyapp.presentation.login.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.hardbobby.cryptocurrencyapp.presentation.base.BaseViewModel
import com.hardbobby.cryptocurrencyapp.presentation.utils.LoginFlow
import com.hardbobby.cryptocurrencyapp.presentation.utils.UserManager
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    val userManager: UserManager
) : BaseViewModel() {

    private var _loginFlow = MutableLiveData<LoginFlow>()
    val loginFlow = _loginFlow as LiveData<LoginFlow>

    init {
        onCheckUserAuth()
    }

    private fun onCheckUserAuth() {
        if (userManager.isSessionActive()) {
            navigate(LoginFlow.MainHomePage)
        } else {
            userManager.endUserSession()
            navigate(LoginFlow.FormLogin)
        }
    }

    fun onUserLogin(emailUser: String) {
        saveSession(emailUser)
        postSnackbar("Login Berhasil")
        navigate(LoginFlow.MainHomePage)
    }

    private fun navigate(flow: LoginFlow) {
        _loginFlow.postValue(flow)
    }

    private fun saveSession(emailUser: String) {
        userManager.startUserSession().also {
            userManager.email = emailUser
        }
    }
}