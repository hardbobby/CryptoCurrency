package com.hardbobby.domain.dto

data class WebSocketModelView(
    var type: Int? = 0,
    var symbol: String? = "",
    var topTierFullVolume: Double? = 0.0
)
