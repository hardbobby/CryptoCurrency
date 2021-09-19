package com.hardbobby.data.mapper

import com.hardbobby.data.database.entity.WebSocketEntity
import com.hardbobby.domain.dto.WebSocketResponse

class WebSocketDaoMapper : Mapper<WebSocketResponse, WebSocketEntity> {
    override fun mapFromResponse(
        response: WebSocketResponse?,
        pageNum: Int
    ): WebSocketEntity {
        return WebSocketEntity(
            response?.type,
            response?.symbol,
            response?.fullVolume,
            System.currentTimeMillis()
        )
    }

    override fun mapFromDao(model: WebSocketEntity?): WebSocketResponse {
        return WebSocketResponse(model?.type, model?.symbol, model?.fullVolume)

    }
}