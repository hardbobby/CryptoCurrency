package com.hardbobby.data.repositoryimpl

import com.hardbobby.data.common.Constants.WEB_SOCKET_SUBSCRIBE_MODEL
import com.hardbobby.data.common.networkBoundResource
import com.hardbobby.data.common.networkBoundResourceStream
import com.hardbobby.data.database.dao.CryptoDao
import com.hardbobby.data.database.dao.WebSocketDao
import com.hardbobby.data.mapper.CryptoDaoMapper
import com.hardbobby.data.mapper.WebSocketDaoMapper
import com.hardbobby.data.remote.CryptoAPI
import com.hardbobby.data.remote.WebSocketApi
import com.hardbobby.domain.common.SimpleResult
import com.hardbobby.domain.common.mapToResult
import com.hardbobby.domain.dto.CryptoRequest
import com.hardbobby.domain.dto.CryptoResponse
import com.hardbobby.domain.dto.WebSocketResponse
import com.hardbobby.domain.repository.CryptoRepository
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class CryptoRepositoryImpl @Inject constructor(
    private val cryptoAPI: CryptoAPI,
    private val cryptoDao: CryptoDao,
    private val cryptoDaoMapper: CryptoDaoMapper,
    private val webSocketDaoMapper: WebSocketDaoMapper,
    private val webSocket: WebSocketApi,
    private val webSocketDao: WebSocketDao
) : CryptoRepository {

    override suspend fun getCryptoData(param: CryptoRequest): Flow<SimpleResult<List<CryptoResponse>>> {
        return networkBoundResource(
            query = {
                cryptoDao.getListCrypto(pageNo = param.pageNum).map {
                    cryptoDaoMapper.mapFromDao(it)
                }
            },
            fetch = {
                delay(2000)
                cryptoAPI.getCryptoList(param.limit, param.pageNum, param.tsym).mapToResult()
            },
            saveFetchResult = {
                cryptoDao.deleteOldCrypto(param.pageNum)
                cryptoDaoMapper.mapFromResponse(it, param.pageNum).map { crypto ->
                    cryptoDao.insertOrUpdateCrypto(crypto)
                }
            },
            isDataCacheAvailable = {
                !it.isNullOrEmpty()
            }
        )
    }


    override suspend fun getWebSocketData(): Flow<SimpleResult<WebSocketResponse>> {
        return networkBoundResourceStream(
            queryList = {
                webSocketDao.getListWebSocket().map {
                    it.map { webSocket ->
                        webSocketDaoMapper.mapFromDao(webSocket)
                    }
                }
            },
            query = {
                webSocketDao.getWebSocket().map {
                    webSocketDaoMapper.mapFromDao(it)
                }
            },
            fetch = {
                delay(2000)
                webSocket.subscribe(WEB_SOCKET_SUBSCRIBE_MODEL)
                webSocket.observeResponse()
            },
            saveFetchResult = {
                webSocketDao.deleteOldWebSocket(it.type ?: 0)
                val webSocket = webSocketDaoMapper.mapFromResponse(it, 0)
                webSocketDao.insertOrUpdateWebSocket(webSocket)
            }
        )
    }
}