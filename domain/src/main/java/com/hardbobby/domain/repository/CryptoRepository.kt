package com.hardbobby.domain.repository

import com.hardbobby.domain.common.SimpleResult
import com.hardbobby.domain.dto.CryptoRequest
import com.hardbobby.domain.dto.CryptoResponse
import com.hardbobby.domain.dto.WebSocketResponse
import kotlinx.coroutines.flow.Flow

interface CryptoRepository {
    suspend fun getCryptoData(param: CryptoRequest): Flow<SimpleResult<List<CryptoResponse>>>
    suspend fun getWebSocketData(): Flow<SimpleResult<WebSocketResponse>>
}