package com.hardbobby.domain.dto

import com.google.gson.annotations.SerializedName

data class WebSocketResponse(
    @field:SerializedName("TYPE")
    val type: Int?,
    @field:SerializedName("SYMBOL")
    val symbol: String?,
    @field:SerializedName("FULLVOLUME")
    val fullVolume: Double?
)
