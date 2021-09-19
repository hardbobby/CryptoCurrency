package com.hardbobby.domain.usecase

import com.hardbobby.domain.common.Result
import com.hardbobby.domain.common.SimpleResult
import com.hardbobby.domain.common.usecase.BaseUseCase
import com.hardbobby.domain.dto.WebSocketModelView
import com.hardbobby.domain.repository.CryptoRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class WebSocketUseCase @Inject constructor(
    private val cryptoRepository: CryptoRepository
) : BaseUseCase<WebSocketModelView>() {

    override suspend fun execute(): Flow<SimpleResult<WebSocketModelView>> {
        return cryptoRepository.getWebSocketData().map { data ->
            data.mapData( map = { response ->
                Result.Success.Data(
                    WebSocketModelView(
                        response.type,
                        response.symbol,
                        response.fullVolume
                    )
                )
            })
        }
    }
}