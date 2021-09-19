package com.hardbobby.data.remote

import com.hardbobby.domain.dto.SocketSubscription
import com.hardbobby.domain.dto.WebSocketResponse
import com.tinder.scarlet.websocket.WebSocketEvent
import com.tinder.scarlet.ws.Receive
import com.tinder.scarlet.ws.Send
import kotlinx.coroutines.flow.Flow

interface WebSocketApi {

    @Send
    fun subscribe(request: SocketSubscription): Boolean

    @Receive
    fun observeResponse(): Flow<WebSocketResponse>

    @Receive
    fun observeConnection(): Flow<WebSocketEvent>
}