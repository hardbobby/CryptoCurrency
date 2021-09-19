package com.hardbobby.cryptocurrencyapp.presentation.datafeed.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.hardbobby.cryptocurrencyapp.presentation.base.BaseViewModel
import com.hardbobby.domain.common.Result
import com.hardbobby.domain.common.SimpleResult
import com.hardbobby.domain.dto.WebSocketModelView
import com.hardbobby.domain.usecase.WebSocketUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import javax.inject.Inject

@HiltViewModel
class DataFeedViewModel @Inject constructor(
    private val webSocketUseCase: WebSocketUseCase
) : BaseViewModel() {

    private var _webSocketModelView =
        MutableLiveData<SimpleResult<WebSocketModelView>>(Result.State.Loading)
    val webSocketModelView = _webSocketModelView as LiveData<SimpleResult<WebSocketModelView>>

    fun getData() {
        ioLaunch {
            webSocketUseCase.execute().collect {
                _webSocketModelView.postValue(it)
            }
        }
    }
}