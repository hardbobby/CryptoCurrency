package com.hardbobby.cryptocurrencyapp.presentation.watchlist.viewmodel

import MutableLiveEvent
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.hardbobby.cryptocurrencyapp.presentation.base.BaseViewModel
import com.hardbobby.cryptocurrencyapp.presentation.common.Event
import com.hardbobby.domain.common.Result
import com.hardbobby.domain.common.SimpleResult
import com.hardbobby.domain.dto.CryptoModelView
import com.hardbobby.domain.dto.CryptoRequest
import com.hardbobby.domain.usecase.CryptoUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import javax.inject.Inject

@HiltViewModel
class WatchListViewModel @Inject constructor(
    private val cryptoUseCase: CryptoUseCase,
) : BaseViewModel() {

    var cryptoList = mutableListOf<CryptoModelView>()
        private set

    private var currentPage = 1
    private var _cryptoData =
        MutableLiveData<SimpleResult<List<CryptoModelView>>>(Result.State.Loading)
    val cryptoData = _cryptoData as LiveData<SimpleResult<List<CryptoModelView>>>

    private val _loadingNextPage = MutableLiveEvent<Boolean>()
    fun loading() = _loadingNextPage as LiveData<Event<Boolean>>

    fun refreshData() {
        onResetData()
        getData()
    }

    fun getData() {
        ioLaunch {
            val request = CryptoRequest(currentPage)
            cryptoUseCase.execute(request).collect { result ->
                _cryptoData.postValue(result)
                _loadingNextPage.postValue(Event(false))
            }
        }
    }

    private fun onResetData() {
        currentPage = 1
        cryptoList = mutableListOf()
    }

    fun fetchNextPage() {
        if (_loadingNextPage.value?.peekContent() == false) {
            _loadingNextPage.postValue(Event(true))
            currentPage++
            getData()
        }
    }

    fun addListData(data: List<CryptoModelView>) {
        cryptoList.addAll(data)
        cryptoList = cryptoList.distinctBy {
            listOf(it.id, it.name)
        }.toMutableList()
    }

}