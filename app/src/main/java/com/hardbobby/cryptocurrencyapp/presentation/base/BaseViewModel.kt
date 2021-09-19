package com.hardbobby.cryptocurrencyapp.presentation.base

import androidx.lifecycle.*
import androidx.lifecycle.viewModelScope
import com.hardbobby.cryptocurrencyapp.presentation.common.Event
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

open class BaseViewModel : ViewModel()  {

    protected val snackbarMessage = MutableLiveData<Event<String>>()
    fun snackbarMessage() = snackbarMessage as LiveData<Event<String>>

    protected val pageLoading = MutableLiveData<Event<Boolean>>()
    fun pageLoading() = pageLoading as LiveData<Event<Boolean>>

    protected fun postSnackbar(message: String) {
        snackbarMessage.postValue(Event(message))
    }

    protected fun postLoading(show: Boolean) {
        pageLoading.postValue(Event(show))
    }

    protected fun ioLaunch(block: suspend CoroutineScope.() -> Unit) {
        viewModelScope.launch(Dispatchers.IO) {
            block.invoke(this)
        }
    }
}