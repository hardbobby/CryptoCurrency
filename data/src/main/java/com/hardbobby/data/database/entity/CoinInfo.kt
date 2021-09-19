package com.hardbobby.data.database.entity

import androidx.room.Entity

@Entity(tableName = "Coin")
data class CoinInfo(
    val fullName: String,
    val id: String,
    val name: String
)