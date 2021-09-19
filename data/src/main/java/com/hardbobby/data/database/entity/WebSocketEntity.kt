package com.hardbobby.data.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "WebSocket")
data class WebSocketEntity(
    @PrimaryKey
    val type: Int?,
    val symbol: String?,
    val fullVolume: Double?,
    val timeStamp : Long
)
